#include <iostream>
#include <vector>
using namespace std;

void bt(vector<vector<char>>& arr, const vector<char>& balloons, int row, int col, int n, int& count) {
    if (row == n) {
        count++;
        for (const auto& r : arr) {
            for (char c : r) cout << c << ' ';
        }
        cout << endl;
        return;
    }

    if (col == 3) {
        bt(arr, balloons, row + 1, 0, n, count);
        return;
    }

    for (int i = 0; i < 3; i++) {
        if (row == 0) {
            if(!(col == 0 || arr[row][col - 1] != balloons[i])) {
                continue;
            }
        }
        else if (col == 0 && arr[row - 1][col] == balloons[i]) {
            continue;
        }
        else {
            if (arr[row - 1][col] == balloons[i] || arr[row][col - 1] == balloons[i]) {
                continue;
            }
        }
        arr[row][col] = balloons[i];
        bt(arr, balloons, row, col + 1, n, count);
        arr[row][col] = '\0';
    }
}

int solve(int n) {
    vector<char> balloons = {'B', 'W', 'R'};
    int count = 0;
    for (int i = 0; i < 3; i++) {
        vector<vector<char>> arr(n, vector<char>(3, '\0'));
        arr[0][0] = balloons[i];
        bt(arr, balloons, 0, 1, n, count);
    }
    return count;
}

int main() {
    int n;
    cin >> n;
    cout << solve(n) << endl;
    return 0;
}
