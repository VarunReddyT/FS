# A merchant has two types of idols, gold and silver.
# He has arranged the idols in the form of m*n grid, 
# 	- the gold idols are represented as 1's 
# 	- the silver idols are represented as 0's.

# Your task is to find the longest consecutive arrangement of gold idols, 
# The arrangement can be either horizontal, vertical, diagonal or 
# antidiagonal, but not the combination of these.


# Input Format:
# -------------
# Line-1: Two space separated integers m and n, grid size.
# Next m lines : n space separated integers, arrangement of idols.

# Output Format:
# --------------
# Print an integer, longest arranement of gold idols.


# Sample Input:
# ---------------
# 4 5
# 1 0 1 1 1
# 0 1 0 1 0
# 1 0 1 0 1
# 1 1 0 1 1

# Sample Output:
# ----------------
# 4


n,m = list(map(int,input().split()))
arr = [list(map(int, input().split())) for _ in range(n)]
dp = [[[0]*4 for _ in range(m)] for _ in range(n)]
res = 0

for i in range(n):
    for j in range(m):
        if arr[i][j] == 1:
            dp[i][j][0] = (dp[i][j-1][0] if j > 0 else 0) + 1
            dp[i][j][1] = (dp[i-1][j][1] if i > 0 else 0) + 1
            dp[i][j][2] = (dp[i-1][j-1][2] if i > 0 and j > 0 else 0) + 1
            dp[i][j][3] = (dp[i-1][j+1][3] if i > 0 and j < n - 1 else 0) + 1
            
            res = max(res, max(dp[i][j]))

print(res)