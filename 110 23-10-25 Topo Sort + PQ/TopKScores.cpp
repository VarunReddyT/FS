/*
A gaming leaderboard must show top players in real-time as scores update.
We need an efficient way to maintain and query the top K players without
sorting the entire list each time.

Maintain a real-time leaderboard that efficiently returns the top K players by score.

Input Format:
-------------
List of (player, score) tuples
an integer K, number of top players to return

Output Format:
--------------
List of (player, score) tuples of Top 3 players by score.


Sample Input:
-------------
[("Alice", 50), ("Bob", 70), ("Charlie", 60), ("Dave", 40)]
3

Sample Output:
--------------
[("Bob", 70), ("Charlie", 60), ("Alice", 50)]

*/

#include<iostream>
#include <string>
#include <vector>
#include<queue>
using namespace std;

int main(){
    string inp;
    getline(cin,inp);
    int k ;
    cin >> k;
    priority_queue<pair<int,string>> res;
    string curr ="";
    string num = "";
    for(char c : inp){
        if(c =='('){
            curr = "";
            num = "";
        }
        else if(isalpha(c)){
            curr+=c;
        }
        else if(isdigit(c)){
            num+=c;
        }
        else if(c==')'){
            res.push({stoi(num),curr});
        }
    }

    cout<<'[';
    while(!res.empty() && k!=0){
        auto top = res.top();
        cout<< "("<< top.second<<", "<<top.first<<")";
        k--;
        if(k!=0){
            cout<<", ";
        }
        res.pop();
    }
    cout<<"]";

    return 0;
}