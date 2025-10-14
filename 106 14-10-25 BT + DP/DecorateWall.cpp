// Mohan is given a task to decorate a wall with balloons.
// He is given an unlimited set of ballons of three different colors.

// The decoration should have N rows of 3 balloons each. 
// He has to make sure that no two adjacent balloons have the same colour.
// both vertically and horizontally.

// Your task is to help Mr Mohan to find the number of ways to decorate the wall,  
// the answer must be computed modulo 10^9 + 7

// Input Format:
// -------------
// An integer N

// Output Format:
// --------------
// Print an integer as outpur, number of ways to decorate.


// Sample Input-1:
// ---------------
// 1

// Sample Output-1:
// ----------------
// 12

// Explanation:
// ------------
// Suppose the colors are, B-Blue, W-White, R-Red. Given N is 1
// RBR	BRB	WRB
// RBW	BRW	WRW
// RWR	BWR	WBR
// RWB	BWB	WBW


// Sample Input-2:
// ---------------
// 2

// Sample Output-2:
// ----------------
// 54

#include <iostream>
using namespace std;

int main(){
    int n;
    cin >> n;
    long same = 6, diff = 6;
    long mod = 1000000007;
    if(n<=1){
        cout << same + diff;
        return 0;
    }
    for(int i = 2;i<=n;i++){
        long t = same;
        same = (same*3 + diff * 2)%mod;
        diff = (diff*2 + t*2)%mod;
    }
    cout << (int)((same+diff)%mod);
    return 0;
}