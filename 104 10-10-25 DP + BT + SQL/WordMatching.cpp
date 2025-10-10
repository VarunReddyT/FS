// Given two strings S1 and S2, find if S2 can match S1 or not.

// A match that is both one-to-one (an injection) and onto (a surjection), 
// i.e. a function which relates each letter in string S1 to a separate and 
// distinct non-empty substring in S2, where each non-empty substring in S2
// also has a corresponding letter in S1.

// Return true,if S2 can match S1.
// Otherwise false.

// Input Format:
// -------------
// Line-1 -> Two strings S1 and S2

// Output Format:
// --------------
// Print a boolean value as result.


// Sample Input-1:
// ---------------
// abab kmitngitkmitngit

// Sample Output-1:
// ----------------
// true


// Sample Input-2:
// ---------------
// aaaa kmjckmjckmjckmjc

// Sample Output-2:
// ----------------
// true

// Sample Input-3:
// ---------------
// mmnn pqrxyzpqrxyz

// Sample Output-3:
// ----------------
// false

#include<iostream>
#include<string>
#include<unordered_map>
#include<unordered_set>
using namespace std;

bool backtrack(string &s1, string &s2, int index1, int index2, unordered_map<char, string> &map, unordered_set<string> &set) {
    if (index1 == s1.size() && index2 == s2.size()) {
        return true;
    }
    if (index1 == s1.size() || index2 == s2.size()) {
        return false;
    }

    char c = s1[index1];
    if (map.find(c) != map.end()) {
        string curr = map[c];
        if (s2.substr(index2, curr.size()) != curr) {
            return false;
        }
        return backtrack(s1, s2, index1 + 1, index2 + curr.size(), map, set);
    }

    for (int i = index2 + 1; i <= s2.size(); i++) {
        string sub = s2.substr(index2, i - index2);
        if (set.find(sub) != set.end()) {
            continue;
        }

        map[c] = sub;
        set.insert(sub);

        if (backtrack(s1, s2, index1 + 1, i, map, set)) {
            return true;
        }

        map.erase(c);
        set.erase(sub);
    }

    return false;
}

bool isMatching(string s1, string s2) {
    unordered_map<char, string> map;
    unordered_set<string> set;
    return backtrack(s1, s2, 0, 0, map, set);
}

int main() {
    string s1, s2;
    cin >> s1 >> s2;
    cout << (isMatching(s1, s2) ? "true" : "false") << endl;
    return 0;
}
