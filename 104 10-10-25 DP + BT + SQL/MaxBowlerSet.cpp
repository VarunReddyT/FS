// BCCI wants to select the group of bowlers for an upcoming test-series, 
// you want to choose the group with highest number of wickets, which is 
// the sum of wickets taken by all the bowlers in that group.

// However, the bowler group is not allowed to have any disputes. A dispute
// exists if a younger bowler has strictly highest wickets than an older bowler. 
// A dispute does not occur between bowlers of the same age.

// You are given information of N bowlers as two lists, wickets and ages, 
// where each wickets[i] and ages[i] represents the wickets and age of 
// the i-th bowler, respectively, return the highest number of wickets 
// of all possible bowler groups.


// Input Format:
// -------------
// Line-1: An integer N, number of bowlers.
// Line-2: N space separated integers, wickets[].
// Line-3: N space separated integers, ages[].

// Output Format:
// --------------
// An integer, highest number of wickets of all possible bowler groups.


// Sample Input-1:
// ---------------
// 4
// 5 3 5 6
// 3 5 4 2

// Sample Output-1:
// ----------------
// 10

// Explanation:
// ------------
// It is best to choose 2 bowlers of age 3 and 4. 


// Sample Input-2:
// ---------------
// 5
// 5 3 5 6 3
// 2 5 4 2 1

// Sample Output-2:
// ----------------
// 14

// Explanation:
// ------------
// It is best to choose 3 bowlers of age 1 and 2. 
// Notice that you are allowed to choose multiple bowlers of the same age.

// Sample Input-3:
// ---------------
// 5
// 1 3 5 10 15
// 1 2 3 4 5

// Sample Output-3:
// ----------------
// 34

// Explanation:
// ------------
// You can choose all the bowlers.


#include<iostream>
#include<utility>
#include<algorithm>
#include <vector>
using namespace std;

int getBowlers(vector<pair<int,int>> arr, int n);

int main(){
    vector<pair<int,int>> arr;
    int n;
    cin >> n;

    vector<int> wick(n), age(n);

    for(int i = 0;i<n;i++){
        cin >> wick[i];
    }
    for(int i = 0;i<n;i++){
        cin >> age[i];
    }
    for(int i = 0;i<n;i++){
        arr.push_back({age[i], wick[i]});
    }
    cout << getBowlers(arr,n);
    return 0;
}

int getBowlers(vector<pair<int,int>> arr, int n){
    vector<int> dp(n);
    sort(arr.begin(), arr.end());
    int res = 0;
    for(int i = 0;i<n;i++){
        dp[i] = arr[i].second;
        for(int j = 0;j<i;j++){
            if(arr[i].first == arr[j].first || arr[i].second >= arr[j].second){
                dp[i] = max(dp[i], dp[j] + arr[j].second);
            }
            res = max(res,dp[i]);
        }
    }
    return res;
}