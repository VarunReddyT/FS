# Mr.Edwards is in hunt of the treasure, the treaure is inside the locker of 
# a room where the floor of the room is in square shape. 
# The floor of size N*N, and it is decorated two different colored tiles,
# white color (0) tiles and black color (1) tiles.

# Initially, Mr Edwards is at (0,0) tile and the treasure is at (N-1, N-1) tile, 
# and both the tiles are white colored.
# The rules to reach the treasure are as follows:
#     - He can move only one step at a time to the adjacent tile.
#     - The adjacent tile can be adjacent horizontal,vertical or diagonal.
#     - He need to step on only white colored tile.
    
# Your task is to help Mr Edwards to reach the Treasure, and 
# print the minimum number of steps required to reach the treasure.
# If it is not possible to reach the treasure, print -1.

# Input Format:
# -------------
# Line-1: An integer N, size of the floor N*N.
# Next N lines: N space separated integers, the floor arragement.

# Output Format:
# --------------
# Print an integer result.


# Sample Input-1:
# ---------------
# 4
# 0 0 0 1
# 1 0 1 1
# 0 1 1 0
# 1 0 0 0

# Sample Output-1:
# ----------------
# 6


# Sample Input-2:
# ---------------
# 5
# 0 0 1 1 1
# 0 1 0 1 0
# 0 1 1 0 1
# 0 1 0 1 1
# 1 0 1 0 0

# Sample Output-2:
# ----------------
# 7

from collections import deque

n = int(input())
floor = [list(map(int, input().split())) for _ in range(n)]

def is_valid(x, y):
    return 0 <= x < n and 0 <= y < n and floor[x][y] == 0

def min_steps_to_treasure():
    if floor[0][0] == 1 or floor[n-1][n-1] == 1:
        return -1
    
    dirX = [-1, -1, -1, 0, 0, 1, 1, 1]
    dirY = [-1, 0, 1, -1, 1, -1, 0, 1]
    visited = [[False] * n for _ in range(n)]
    queue = deque([(0, 0, 1)])  # (x, y, steps)
    visited[0][0] = True
    
    while queue:
        x,y,steps = queue.popleft()
        
        if x == n-1 and y == n-1:
            return steps
        
        for i in range(8):
            newX, newY = x + dirX[i], y + dirY[i]
            if is_valid(newX, newY) and not visited[newX][newY]:
                visited[newX][newY] = True
                queue.append((newX, newY, steps + 1))
    
    return -1

print(min_steps_to_treasure())