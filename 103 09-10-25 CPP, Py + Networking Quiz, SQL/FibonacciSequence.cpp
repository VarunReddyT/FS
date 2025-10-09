// Pramod is working on Strings consist of digits only. He wants to findout, 
// whether the given string can form Fibonacci sequence or not.

// A String can form a Fibonacci Sequence, if it contains at least 
// three numbers, and numbers are in the following order:
// first, second, third  = first + second, fourth = third + second, .. so on.

// Return true, if the given string can form fibonacci sequence,
// otherwise, return false.

// Note: Numbers in the fibonacci sequence contains no leading 0's.
// for example, 2, 03,5 or 2,3,05 or 02,3,5 are not valid.

// Input Format:
// -------------
// A String consist of digits only

// Output Format:
// --------------
// Print a boolean value as result.


// Sample Input-1:
// ---------------
// 23581321

// Sample Output-1:
// ----------------
// true

// Explanation: 
// ------------
// Fibonacci Sequence is : 2, 3, 5, 8, 13, 21
// 2, 3, 2+3=5, 3+5=8, 5+8=13, 8+13=21.

// Sample Input-2:
// ---------------
// 199100199

// Sample Output-2:
// ----------------
// true

// Explanation: 
// ------------
// Fibonacci Sequence is : 1 99 100 199
// 1, 99, 1+99=100, 99+100=199.



#include<iostream>
#include<string>
using namespace std;

bool find(unsigned long long a, unsigned long long b, string s, string n){
    if(s.size() > n.size()){
        return false;
    }
    if(n.rfind(s,0) != 0){
        return false;
    }
    if(s.size() == n.size()){
        return s==n;
    }
    unsigned long long c = a + b;
    return find(b,c,s+to_string(c),n);
}

bool isFibo(string n){
    int len = n.size();
    for(int i = 1;i<=len/2;i++){
        for(int j = i+1;j<len;j++){
            string a = n.substr(0,i);
            string b = n.substr(i,j-i);
            if((a.size() >  1 && a[0] == '0') || (b.size() > 1 && b[0] == '0')){
                continue;
            }
            if(find(stoull(a), stoull(b), a+b , n)){
                return true;
            }
        }
    }
    return false;
}

int main(){
    string n;
    cin >> n;
    cout << (isFibo(n)==true ? "true" : "false");
    return 0;
}