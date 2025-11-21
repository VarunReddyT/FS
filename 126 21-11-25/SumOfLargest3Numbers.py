# You are given an array of integers. You must return the sum of 
# the 3 largest numbers in the array that satisfy this condition:
# 	- A number must be divisible by its own last digit.
# 	- Critical Rule: If a number ends with 0, it cannot be used.
# 	- If fewer than three numbers satisfy the condition:
# 	  Sum whatever valid numbers exist.
# 	- If no number satisfies the condition, return 0.
	
# Input Format:
# -------------
# A single line of integers separated by spaces.

# Output Format:
# -----------------
# An Integer


# Samples:
# Input: 12 24 23 55 10
# Output: 91
# Explanation: Valid numbers: 12 (12 % 2 == 0), 24 (24 % 4 == 0), 
# 55 (55 % 5 == 0) → largest three = 55, 24, 12

# Input: 21 31 41 51
# Output: 123
# Explanation: All end with 1 and are divisible by 1 → largest three = 51, 41, 31

# Input: 20 30 100 50
# Output: 0
# Explanation: All end with 0 → none valid
