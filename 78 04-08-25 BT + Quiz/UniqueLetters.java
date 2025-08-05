// import java.util.*;

// public class Solution{
//     public static String getString(String s){
//         HashSet<Character> unique = new HashSet<>();
//         for(Character c : s.toCharArray()){
//             unique.add(c);
//         }
//         String res = "";
//         for(int i = 0;i<s.length();i++){
//             String temp = "";
//             HashSet<Character> set = new HashSet<>();
//             for(int j = i;j<s.length();j++){
//                 if(!set.contains(s.charAt(j))){
//                     set.add(s.charAt(j));
//                     temp += s.charAt(j);
//                 }
//             }
//             if(temp.length() == unique.size()){
//                 if(res.equals("")){
//                     res = temp;
//                 }
//                 else if(temp.compareTo(res) < 0){
//                     res = temp;
//                 }
//             }
//         }
//         return res;
//     }
    
//     public static void main(String[] args){
//         Scanner sc = new Scanner(System.in);
//         String s = sc.nextLine();
//         System.out.println(getString(s));
//     }
// }


import java.util.*;

public class UniqueLetters{
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String inp = sc.next();
        StringBuilder res = new StringBuilder();
        Set<Character> set = new HashSet<>();
        find(set,inp,new StringBuilder(),res,0);
        System.out.println(res);
        sc.close();
    }
    static void find(Set<Character> set , String inp, StringBuilder curr, StringBuilder res, int ind){
        if(ind==inp.length()){
            if(curr.length()>res.length()){
                res.setLength(0);
                res.append(curr.toString());
            }
            else if(curr.length()==res.length()&& curr.toString().compareTo(res.toString())<0){
                res.setLength(0);
                res.append(curr.toString());
            }
            return;
        }
        
        if(!set.contains(inp.charAt(ind))){
            curr.append(inp.charAt(ind));
            set.add(inp.charAt(ind));
            find(set,inp,curr,res,ind+1);
            set.remove(inp.charAt(ind));
            curr.deleteCharAt(curr.length()-1);
        }
        
        find(set,inp,curr,res,ind+1);
    }

}