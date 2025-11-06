# Pandavas and Kauravas are ruling two different kingdoms sepertaed by river.
# If you connect both the kingdoms, they will be in Square shaped land.
# Land occupied by the kingdoms indiacted with 1's and the river indiacted with 0's.

# Now, Pandavas and Kauravas decided to build a bridge on the river for easy 
# connectivity. As the cost of building a bridge will be high, they have decided 
# to reduce the length of the bridge. You are allowed to build the bridge on 
# the river cells, connected by 4 directions only (up, down,left,right).

# Your task is to help the Kings, to minimize the occupation of river cells, 
# to build the bridge with minimum cost. And return the count of river cells occupied.

# Input Format:
# -------------
# Line-1: An integer N, size of the land.
# Next N lines: N space separated integers, either 0 or 1. 

# Output Format:
# --------------
# Print an integer result.


# Sample Input-1:
# ---------------
# 4
# 1 1 1 0
# 1 0 0 0
# 1 0 0 1
# 0 0 1 1

# Sample Output-1:
# ----------------
# 2


# Sample Input-2:
# ---------------
# 5
# 1 1 0 0 0
# 1 1 0 0 0
# 0 0 0 0 1
# 0 0 0 1 1
# 0 0 1 1 1

# Sample Output-2:
# ----------------
# 3

from collections import deque

n = int(input())

arr = [list(map(int, input().split())) for _ in range(n)]

def dfs(arr, i, j, n, visited, q):
    if i<0 or i>=n or j<0 or j>=n or visited[i][j] or arr[i][j] == 0:
        return
    visited[i][j] = True
    q.append((i,j))
    
    dfs(arr,i+1,j,n,visited,q)
    dfs(arr,i-1,j,n,visited,q)
    dfs(arr,i,j+1,n,visited,q)
    dfs(arr,i,j-1,n,visited,q)
    
    # visited[i][j] = False

def getSteps(arr, n):
    visited = [[False]*n for _ in range(n)]
    q = deque()
    res = 0
    flag = False
    for i in range(n):
        if flag:
            break
        for j in range(n):
            if arr[i][j] == 1:
                dfs(arr,i,j,n,visited,q)
                flag = True
                break
    
    dirX = [-1,0,1,0]
    dirY = [0,1,0,-1]
    
    while q:
        size = len(q)
        for _ in range(size):
            r, c = q.popleft()
            for i in range(4):
                nr = r + dirX[i]
                nc = c + dirY[i]
                
                if nr>= 0 and nr<n and nc>=0 and nc<n and not visited[nr][nc]:
                    if arr[nr][nc] == 1:
                        return res
                    visited[nr][nc] = True
                    q.append((nr,nc))
        res += 1      
    
    return res
    
print(getSteps(arr,n))