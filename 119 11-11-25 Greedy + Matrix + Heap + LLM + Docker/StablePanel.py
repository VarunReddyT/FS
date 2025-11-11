# AMB mall to attract kids and to increase their economy they came to have 
# a separate Fun Zone. They opened a stall by name Soda Bears. We have an 
# LED display panel with different colours of Soda bears.

# You will be given a 2D integer panel representing the grid of a 
# Soda bears, we have different color-codes as positive integer in 
# panel [p][q] of each coloured soda bears. If a cell in 
# panel[p][q]=0 representing at position (p,q) is empty. 

# The given panel represents the state of game according to participants move. 
# Now it’s your aim to make panel to a stable state by merging Soda Bears 
# with certain conditions:

# 1. If three or more soda bears of the same colour are adjacent 
# vertically or horizontally,"merge" them all at the same time - 
# these locations become empty.

# 2. After merging all Soda Bears simultaneously, if an empty space on
# the panel has soda bears on top of itself, then these bears will drop
# until they hit another bear or bottom at the same time. 
# (No new soda bear will drop outside the top boundary.)

# 3. After the above steps, there may exist more bears that can be merged. 
# If so, you need to repeat the above steps.

# 4. If there does not exist more bears for merge (ie. the panel is stable), 
# then return the current panel.

# Repeat the procedure for stable panel, then return the current panel state.

# Input Format:
# -------------
# Line-1: Two space separated integers, M and N size of panel
# Next M lines: N space separated integers, color codes of soda bears.

# Output Format:
# --------------
# Print the stable format of panel.


# Sample Input:
# --------------
# 8 4
# 11 5 13 5
# 12 13 5 13
# 1 2 3 4
# 11 2 3 4
# 2 2 2 5
# 13 13 3 4
# 14 13 13 14
# 12 12 11 13

# Sample Output:
# ----------------
# 0 0 0 5
# 11 0 0 13
# 12 0 0 4
# 1 0 0 4
# 11 0 13 5
# 13 0 5 4
# 14 5 13 14
# 12 12 11 13

n, m = map(int,input().split())

grid = [list(map(int, input().split())) for _ in range(n)]


def stablePanel(grid, n, m):
    while True:
        pos = set()
        flag = False
        for i in range(n):
            for j in range(m-2):
                if grid[i][j] != 0 and grid[i][j] == grid[i][j+1] == grid[i][j+2]:
                    flag = True
                    pos.add((i,j))
                    pos.add((i,j+1))
                    pos.add((i,j+2))
        for i in range(n-2):
            for j in range(m):
                if grid[i][j] != 0 and grid[i][j] == grid[i+1][j] == grid[i+2][j]:
                    flag = True
                    pos.add((i,j))
                    pos.add((i+1,j))
                    pos.add((i+2,j))
        
        if not flag:
            break
        
        for i,j in pos:
            grid[i][j] = 0
            
        for j in range(m):           
            write_row = n - 1        
            for i in range(n - 1, -1, -1):
                if grid[i][j] != 0: 
                    grid[write_row][j] = grid[i][j]
                    write_row -= 1
            for k in range(write_row, -1, -1):
                grid[k][j] = 0
            
    for i in grid:
        print(*i)
stablePanel(grid, n, m)