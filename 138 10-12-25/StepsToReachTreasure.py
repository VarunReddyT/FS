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
