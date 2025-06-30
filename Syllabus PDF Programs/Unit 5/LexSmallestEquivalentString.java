// . Lexicographically Smallest Equivalent String:
// You are given two strings of the same length s1 and s2 and a string baseStr.
// We say s1[i] and s2[i] are equivalent characters.
// For example, if s1 = "abc" and s2 = "cde", then we have 'a' == 'c', 'b' == 'd', and 'c' == 'e'.
// Equivalent characters follow the usual rules of any equivalence relation:
// Reflexivity: 'a' == 'a'.
// Symmetry: 'a' == 'b' implies 'b' == 'a'.
// Transitivity: 'a' == 'b' and 'b' == 'c' implies 'a' == 'c'.
// For example, given the equivalency information from s1 = "abc" and s2 = "cde", "acd" and "aab" 
// are equivalent strings of baseStr = "eed", and "aab" is the lexicographically smallest equivalent 
// string of baseStr.
// Return the lexicographically smallest equivalent string of baseStr by using the equivalency 
// information from s1 and s2.
// Example 1:
// Input: s1 = "parker", s2 = "morris", baseStr = "parser"
// Output: "makkek"
// Explanation: Based on the equivalency information in s1 and s2, we can group their characters as 
// [m,p], [a,o], [k,r,s], [e,i].
// The characters in each group are equivalent and sorted in lexicographical order.
// So the answer is "makkek".
// Example 2:
// Input: s1 = "hello", s2 = "world", baseStr = "hold"
// Output: "hdld"
// Explanation: Based on the equivalency information in s1 and s2, we can group their characters as 
// [h,w], [d,e,o], [l,r].
// So only the second letter 'o' in baseStr is changed to 'd', the answer is "hdld".
// Example 3:
// Input: s1 = "leetcode", s2 = "programs", baseStr = "sourcecode"
// Output: "aauaaaaada"
// Explanation: We group the equivalent characters in s1 and s2 as [a,o,e,r,s,c], [l,p], [g,t] 
// and [d,m], thus all letters in baseStr except 'u' and 'd' are transformed to 'a',
// the answer is "aauaaaaada"

import java.util.*;

public class LexSmallestEquivalentString {
    private class UnionFind {
        private int[] parent;

        private UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        private int getAbsoluteParent(int i) {
            if (parent[i] == i) {
                // absolute parent
                return i;
            }
            parent[i] = getAbsoluteParent(parent[i]);
            return parent[i];
        }

        private void unify(int i, int j) {
            int absoluteParentI = getAbsoluteParent(i);
            int absoluteParentJ = getAbsoluteParent(j);
            if (absoluteParentI < absoluteParentJ) {
                parent[absoluteParentJ] = absoluteParentI;
            } else {
                parent[absoluteParentI] = absoluteParentJ;
            }
        }
    }

    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        UnionFind uf = new UnionFind(26);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s1.length(); i++) {
            int charS1 = s1.charAt(i) - 'a';
            int charS2 = s2.charAt(i) - 'a';
            uf.unify(charS1, charS2);
        }
        for (int i = 0; i < baseStr.length(); i++) {
            int smallestMappedChar = uf.getAbsoluteParent(baseStr.charAt(i) - 'a');
            sb.append((char) (smallestMappedChar + 'a'));
        }
        return sb.toString();
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String A = sc.next();
        String B = sc.next();
        String T = sc.next();
        LexSmallestEquivalentString lses = new LexSmallestEquivalentString();
        System.out.println(lses.smallestEquivalentString(A, B, T));
        sc.close();
    }
}