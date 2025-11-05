# Venkatesh is a maths teacher.
# He is teaching matrices concept to his students.
# He is given a matrix of size m*n, and it contains only positive numbers.
# He has given a task to his students to find the number of special matrices 
# in the given matrix A[m][n].

# A special matrix has following property:
# 	- The size of matrix should be 3*3,
# 	- The sum of elements in each row, each column and 
# 	  the two diagonals are equal.
# 	- The 3*3 matrix should contains all the numbers from 1 to 9.
	
# Your task is to help the students to find the number of speical matrices
# in the given matrix.

# Input Format:
# -------------
# Line-1: Two space separated integers M and N, size of the matrix.
# Next M lines: N space separated integers m and n.

# Output Format:
# --------------
# Print an integer, number of the special matrices.


# Sample Input-1:
# ---------------
# 4 5
# 6 8 1 6 8
# 7 3 5 7 3
# 2 4 9 2 4
# 6 8 1 6 8

# Sample Output-1:
# ----------------
# 1

# Explanation:
# ------------
# The special square is:
# 8 1 6
# 3 5 7
# 4 9 2


# Sample Input-2:
# ---------------
# 3 5
# 2 7 6 7 2
# 9 5 1 5 9
# 4 3 8 3 4

# Sample Output-2:
# ----------------
# 2

# Explanation:
# ------------
# The special squares are:
# 2 7 6
# 9 5 1
# 4 3 8
# -----
# 6 7 2
# 1 5 9
# 8 3 4

n, m = list(map(int,input().split()))
mat = [list(map(int, input().split())) for _ in range(n)]

def isSpecial(mat, i, j, n, m):
    nums = set()
    for p in range(i, i+3):
        for q in range(j, j+3):
            nums.add(mat[p][q])
    
    for p in range(1,10):
        if p not in nums:
            return False
    
    rowSum = 0
    colSum = 0
    
    for col in range(j, j+3):
        rowSum += mat[i][col]
    for row in range(i+1, i+3):
        tempRowSum = mat[row][j] + mat[row][j+1] + mat[row][j+2]
        if tempRowSum != rowSum:
            return False
    
    for row in range(i, i+3):
        colSum += mat[row][j]
    for col in range(j+1, j+3):
        tempColSum = mat[i][col] + mat[i+1][col] + mat[i+2][col]
        if tempColSum != colSum:
            return False
    
    if rowSum != colSum:
        return False
    
    diagonalSum = mat[i][j] + mat[i+1][j+1] + mat[i+2][j+2]
    
    if diagonalSum != rowSum:
        return False
    
    antiDiagonalSum = mat[i][j+2] + mat[i+1][j+1] + mat[i+2][j]
    
    if antiDiagonalSum != diagonalSum:
        return False
    
    return True

def countSpecialMatrices(mat,n,m):
    if n<3 or m<3:
        return 0
    res = 0
    for i in range(n-2):
        for j in range(m-2):
            if isSpecial(mat,i,j,n,m):
                res += 1
    return res

print(countSpecialMatrices(mat,n,m))