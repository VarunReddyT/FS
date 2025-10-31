# Aravind went to a forest, he stuck inside the forest and He is blind.
# The forest is given as a square grid. The forest grid is filled with trees 
# and empty cells. He can move upwards, downwadrs, left and right, 
# but he cannot stop until he touches the tree. Once he touches a tree, 
# he can choose the next direction to move. Intially Aravind is at poistion A, 
# and he is trying to reach a safe-point at position S.

# You will be given the forest grid filled with 1's and 0's, 1 means tree 
# and 0 means empty cell. And initial position of Aravind, safe-point S.

# Your task is to find the minimum distance travelled by Aravind to reach 
# the safe-point, If he cannot stop at the safe-point, return -1.

# You may assume that the borders of the forest are all trees.


# Input Format:
# -------------
# Line-1: An integer N, size of the grid.
# Next N lines: N space separated integers
# Next line:  two space separated integers, initial position of Aravind
# Next line:  two space separated integers, position of safe-point.

# Output Format:
# --------------
# Print an integer, minimum distance travelled by Aravind to reah safe-point.


# Sample Input-1:
# ---------------
# 5
# 0 1 0 0 0
# 0 0 0 0 0
# 0 0 0 1 0
# 1 1 0 0 1
# 0 0 0 0 0
# 2 4
# 4 0

# Sample Output-1:
# ----------------
# 10

# Explanation:
# ------------
# For Picture look at hint.
# The minimum path is : up -> left -> down -> left.

# Sample Input-2:
# ---------------
# 5
# 0 1 0 0 0
# 0 0 0 0 0
# 0 0 0 1 0
# 1 1 0 0 1
# 0 0 0 0 0
# 0 3
# 3 3

# Sample Output-2:
# ----------------
# -1

# Explanation: 
# ------------
# Aravind cannot stop at safe-point.



from collections import deque

n = int(input())
grid = [list(map(int,input().split())) for _ in range(n)]
init = list(map(int,input().split()))
safe = list(map(int,input().split()))
dp = [[float('inf')] * n for _ in range(n)]
dp[init[0]][init[1]] = 0

q = deque()
q.append((init[0], init[1]))
dirs = [
    [1, 0],   
    [-1, 0],
    [0, 1],   
    [0, -1]   
]

while q:
    x, y = q.popleft()
    for dx, dy in dirs:
        nx, ny = x, y
        steps = 0
        while 0 <= nx + dx < n and 0 <= ny + dy < n and grid[nx + dx][ny + dy] == 0:
            nx += dx
            ny += dy
            steps += 1
        if dp[x][y] + steps < dp[nx][ny]:
            dp[nx][ny] = dp[x][y] + steps
            q.append((nx, ny))

res = dp[safe[0]][safe[1]]
print(-1 if res == float('inf') else res)