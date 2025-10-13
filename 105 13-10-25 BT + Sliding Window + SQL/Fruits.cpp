// Given a bunch of baskets in a row, each basket contains few fruits in it.
// You can select K number of baskets, one after other.
// The selection of basket is either from beginning or from the end the row.

// Return the maximum number of fruits you have after K baskets collected.

// Input Format:
// -------------
// Line-1 -> Two integers N and K, N is no of baskets and 
//           K is the basket count you can pick.
// Line-2 -> N space separated integers fruits[], fruits in each basket.

// Output Format:
// --------------
// Print an integer, Maximum num of fruits you can collect.


// Sample Input-1:
// ---------------
// 7 3
// 2 3 5 4 1 2 3 

// Sample Output-1:
// ----------------
// 10


// Sample Input-2:
// ---------------
// 5 3
// 2 2 2 2 2

// Sample Output-2:
// ----------------
// 6


// Sample Input-3:
// ---------------
// 8 3
// 1 79 80 1 1 1 200 1

// Sample Output-3:
// ----------------
// 202


#include<iostream>
#include<vector>
using namespace std;

int main(){
    int n, k;
    cin >> n >> k;
    vector<int> arr(n);
    for(int i = 0;i<n;i++){
        cin >> arr[i];
    }
    int b = k-1;
    int res = 0, sum = 0;
    for(int i = 0;i<k;i++){
        sum += arr[i];
    }
    while(b >= 0){
        res = max(res, sum);
        sum -= arr[b];
        sum += arr[n-1];
        n--;
        b--;
    }
    res = max(res, sum);
    cout << res;
    return 0;
}