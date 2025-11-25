# Madhu is given strings, Word W and Pattern P.
# W contains only lowercase letters, P contains lowercase letters and $, *.

# In the pattern:
# '$' indicates any single lowercase letter.
# '*' indicates zero more number of lowercase letters.

# Madhu's task is to find out, whether Pattern is matching the complete Word or not.
# If pattern match the complete word, print true.
# Otherwise false.

# Input Format:
# -------------
# Two strings, Word and Pattern

# Output Format:
# --------------
# Print the boolean result


# Sample Input-1:
# ---------------
# aa *

# Sample Output-1:
# ----------------
# true


# Sample Input-2:
# ---------------
# cb $a

# Sample Output-2:
# ----------------
# false


# Sample Input-3:
# ---------------
# abcde a*d*

# Sample Output-3:
# ----------------
# true


# Sample Input-4:
# ---------------
# acdcb a*c$b

# Sample Output-4:
# ----------------
# false