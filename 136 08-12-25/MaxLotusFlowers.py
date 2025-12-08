# Arjun has arrived at a serene valley dotted with several lakes, arranged neatly 
# in a rectangular grid. Each location in this valley is marked as either:
# 	* Land, where nothing grows
# 	* Lake, where lotus flowers bloom beautifully

# You are given a 2D grid representing the valley. Each cell (r, c) is:
# 	* 0 → Land cell
# 	* > 0 → Lake cell containing that many lotus flowers

# Arjun wishes to collect the maximum number of lotus flowers possible from 
# these lakes. He is allowed to:
# 	- Start at any lake cell that has lotus flowers.
# 	- When standing on a lake cell (r, c), he may: Pick all lotus flowers from 
# 	that lake (after picking, the lotus count becomes zero), and/or Move to any 
# 	adjacent lake cell — up, down, left, or right 
# 	(but NOT diagonally, and cannot move into or through land)

# Arjun may continue gathering lotus flowers by traveling through any connected 
# group of lake cells. Your task is to determine:
# 	What is the maximum total number of lotus flowers Arjun can collect if he 
# 	chooses the best possible starting lake?

# NOTE:
# -------
# If all cells are land, then Arjun cannot collect any lotus flowers, and the answer is 0.

# Input Format:
# -------------
# Line-1: Two integers R and C, size of the valley.
# Next R lines: C space separated integers, 0- represents land 
# and on-zero represents the lotus flowers.

# Output Format:
# --------------
# Print a single integer, the maximum number of lotus flowers Arjun can collect.


# Sample Input-1:
# ---------------
# 4 4
# 0 2 1 0
# 4 0 0 3
# 1 0 0 4
# 0 3 2 0
 
# Sample Output-1:
# ----------------
# 7


# Sample Input-2:
# ---------------
# 4 4
# 1 0 0 0
# 0 0 0 0
# 0 0 0 0
# 0 0 0 1
 
# Sample Output-2:
# ----------------
# 1
