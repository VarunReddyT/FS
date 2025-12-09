# Riya has discovered an old magic scroll filled with a long sequence of lowercase
# letters. This sequence is actually a secret code, represented as a string s.

# Riya wants to cut the scroll into two pieces so that the magic in both halves 
# stays balanced. She can cut the scroll at any position (except at the very 
# beginning or very end), creating: left part- s_left and right part- s_right
# such that,	s_left + s_right = s (i.e., the two parts together form the original 
# string in order)

# A cut (or split) is called valid if:
# 	- Both s_left and s_right are non-empty, and
# 	- The number of distinct letters in s_left is exactly equal to
# 	  the number of distinct letters in s_right.

# Riya believes that every valid split corresponds to a stable magical 
# configuration of the scroll. Your task is to help Riya find out the 
# number of valid splits can she make.

# Input Format:
# -------------
# A String S, a secret code.

# Output Format:
# --------------
# An integer, number of valid splits.


# Sample Input-1:
# ---------------
# aacaba
 
# Sample Output-1:
# ----------------
# 2

# Explanation:
# -----------
# There are 5 ways to split "aacaba" and 2 of them are valid. 
# ("a", "acaba") Left string and right string contains 1 and 3 distinct letters ❌
# ("aa", "caba") Left string and right string contains 1 and 3 distinct letters ❌
# ("aac", "aba") Left string and right string contains 2 and 2 distinct letters ✅
# ("aaca", "ba") Left string and right string contains 2 and 2 distinct letters ✅
# ("aacab", "a") Left string and right string contains 3 and 1 distinct letters ❌


# Sample Input-2:
# ---------------
# abcd
 
# Sample Output-2:
# ----------------
# 1

