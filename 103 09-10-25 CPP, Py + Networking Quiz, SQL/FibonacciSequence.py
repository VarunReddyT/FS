# Pramod is working on Strings consist of digits only. He wants to findout, 
# whether the given string can form Fibonacci sequence or not.

# A String can form a Fibonacci Sequence, if it contains at least 
# three numbers, and numbers are in the following order:
# first, second, third  = first + second, fourth = third + second, .. so on.

# Return true, if the given string can form fibonacci sequence,
# otherwise, return false.

# Note: Numbers in the fibonacci sequence contains no leading 0's.
# for example, 2, 03,5 or 2,3,05 or 02,3,5 are not valid.

# Input Format:
# -------------
# A String consist of digits only

# Output Format:
# --------------
# Print a boolean value as result.


# Sample Input-1:
# ---------------
# 23581321

# Sample Output-1:
# ----------------
# true

# Explanation: 
# ------------
# Fibonacci Sequence is : 2, 3, 5, 8, 13, 21
# 2, 3, 2+3=5, 3+5=8, 5+8=13, 8+13=21.

# Sample Input-2:
# ---------------
# 199100199

# Sample Output-2:
# ----------------
# true

# Explanation: 
# ------------
# Fibonacci Sequence is : 1 99 100 199
# 1, 99, 1+99=100, 99+100=199.



def find(x, n):
    for i in range(1, n):
        for j in range(i + 1, n):
            a = x[:i]
            b = x[i:j]
            if (len(a) > 1 and a[0] == '0') or (len(b) > 1 and b[0] == '0'):
                continue
            if backtrack(int(a), int(b), str(int(a)) + str(int(b)), x, 2):
                return True
    return False
def backtrack(a, b, s, x, c):
    if s == x:
        return c >= 3
    if not x.startswith(s):
        return False
    n = a + b
    return backtrack(b, n, s + str(n), x, c + 1)
x = input().strip()
print(find(x, len(x)))