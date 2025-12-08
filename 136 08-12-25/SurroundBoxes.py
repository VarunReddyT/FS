# Pranav has a puzzle board filled with square boxes in the form of a grid.
# Some cells in the grid may be empty. '0' - indicates empty, '1' - indicates a box. 

# Pranav wants to find out the number of empty spaces which are completely 
# surrounded by the boxes (left, right, top, bottom) in the board.

# You are given the board in the form of a grid M*N, filled wth 0's and 1's.
# Your task is to help Pranav to find the number of empty groups surrounded by
# the boxes in the puzzle board.

# Input Format:
# -------------
# Line-1: Two integers M and N, the number of rows and columns in the board.
# Next M lines: contains N space-separated either 0 or 1.

# Output Format:
# --------------
# Print an integer, the number of empty spaces in the puzzle board.


# Sample Input-1:
# ---------------
# 6 7
# 1 1 1 1 0 0 1
# 1 0 0 0 1 1 0
# 1 0 0 0 1 1 0
# 0 1 1 1 0 1 0
# 1 1 1 0 0 1 1
# 1 1 1 1 1 1 1

# Sample Output-1:
# ----------------
# 2

# Explanation:
# ------------
# The 2 empty groups are as follows:
# 1st group starts at cell(1,1), 2nd group starts at cell(3,4).
# The groups which are starts at cell(0,4), cell(1,6) and cell(3,0)
# are not valid empty groups, because they are not completely surrounded by boxes.


# Sample Input-2:
# ---------------
# 6 6
# 1 1 0 0 1 1
# 1 0 1 1 0 1
# 0 1 0 1 0 0
# 1 1 0 0 0 1
# 0 0 1 0 1 1
# 1 1 0 1 0 0

# Sample Output-2:
# ----------------
# 1

# Explanation:
# ------------
# The only empty group starts at cell(1,1) is surrounded by boxes.
