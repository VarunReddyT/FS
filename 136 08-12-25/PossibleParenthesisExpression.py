# In the ancient city of Numeria, there lives a famous puzzle master named Arvind, 
# who designs mathematical challenge boxes for elite  problem solvers. Arvind 
# never specifies how the expression should be grouped. Various ways of adding 
# parentheses produce different results.

# Given a string of numbers and operators. Your task is to compute every possible 
# value that can be obtained by fully parenthesizing the expression in all valid 
# ways, and print the list of values in sorted order.

# NOTE:
# The valid operators are +, - and *.

# Input Format:
# -------------
# String exp, numeric expression.

# Output Format:
# --------------
# Return the list of valud results.

# Sample Input-1:
# ---------------
# 2-1-1

# Sample Output-1:
# ----------------
# [0, 2]

# Explanation: 
# -----------
# 	((2-1)-1) = 0 
# 	(2-(1-1)) = 2


# Sample Input-2:
# ---------------
# 2*3-4*5

# Sample Output-2:
# ----------------
# [-34, -14, -10, -10, 10]

# Explanation: 
# -----------
# 	(2*(3-(4*5))) = -34 
# 	((2*3)-(4*5)) = -14 
# 	((2*(3-4))*5) = -10 
# 	(2*((3-4)*5)) = -10 
# 	(((2*3)-4)*5) = 10
