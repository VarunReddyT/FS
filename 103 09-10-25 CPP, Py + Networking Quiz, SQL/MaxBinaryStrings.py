# Mounika is creating the binary strings using P 1's and Q 0's.
# A binary string contains only 0's and 1's.
# She has given a list of binary strings binStr[] to be formed.

# Her task is to find, the maximum number of binary strings can be formed, 
# with given P 1's and Q 0's only. She cannot use any more 1's or 0's.

# Input Format:
# -------------
# Line-1 -> A single line of space separated binary strings, binStr[].
# Line-2 -> Two integers P and Q, P number of 1's and Q number of 0's


# Output Format:
# --------------
# Print an integer as output, maximum number of binary strings can be formed.


# Sample Input-1:
# ---------------
# 10 0001 111001 1 0
# 3 5

# Sample Output-1:
# ----------------
# 4

# Explanation:
# ------------
# She can form 4 strings by the using of 3 1's and 5 0's
# strings are 10, 0001, 1, 0.


# Sample Input-2:
# ---------------
# 10 1 0
# 1 1

# Sample Output-2:
# ----------------
# 2

# Explanation:
# ------------
# She can form 2 strings by the using of 1 1's and 1 0's
# strings are 1, 0.

inp = list(input().split())
p, q = map(int,input().split())

memo = {}

def noOfString(inp, p, q):
    return solve(p,q,inp,0)
    
def solve(p,q,inp,idx):
    if (idx, p, q) in memo:
        return memo[(idx, p, q)]
        
    if p<0 or q<0:
        return float('-inf')
        
    if idx == len(inp) or (p == 0 and q == 0):
        return 0
    
    ones = inp[idx].count('1')
    zeroes = len(inp[idx]) - ones
    
    take = 1 + solve(p-ones,q-zeroes,inp,idx+1)
    not_take = solve(p,q,inp,idx+1)
    memo[(idx,p,q)] = max(take, not_take)
    
    return memo[(idx,p,q)]

print(noOfString(inp,p,q))