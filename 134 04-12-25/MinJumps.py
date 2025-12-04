# You are given a natural number N, and intially you are at position 0.
# Your task is to jump to the position N, in minimum of steps.
# The condition is to jump from a position P is as follows:
# 	- Your initial position P is at 0, P=0.
# 	- You can jump k steps forward or backward, on kth step.
# 	  i.e, either P= P+k, or P=P-k;

# NOTE: N is in the range of [-10^9 , 10^9]

# Input Format:
# -------------
# An integer N

# Output Format:
# --------------
# Print an integer, minimum num of jumps


# Sample Input-1:
# ---------------
# 3

# Sample Output-1:
# ----------------
# 2

# Explanation:
# -------------
# On the first step you jump from 0 to 1.
# On the second step you jump from 1 to 3.


# Sample Input-2:
# ---------------
# 2

# Sample Output-2:
# ----------------
# 3

# Explanation:
# -------------
# On the first step you jump from 0 to 1.
# On the second step you jump  from 1 to -1.
# On the third step you jump from -1 to 2.