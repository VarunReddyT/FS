# Given an 2D character array, Letters[][], of size r*c.
# You have to construct the word W, using the given array.

# Rules to construct the word are as follows:
# 	- All the letters of the word W, should be adjacent to each other 
# 	in the array Letters(either horizontal or vertical).
# 	- You can use each charcater in Letters[][] only once.

# If you are able to construct the word W, return 'true'
# Otherwise 'false'.

# Input Format:
# -------------
# Line-1 -> two integers R and C, array size.
# R lines -> C space separated characters.
# Last line -> a string, word W

# Output Format:
# --------------
# print the boolean result.


# Sample Input-1:
# ---------------
# 3 3
# a b c
# d e f
# g h i
# bad

# Sample Output-1:
# ----------------
# true

# Sample Input-2:
# ---------------
# 3 3
# a b c
# d e f
# g h i
# ace

# Sample Output-2:
# ----------------
# false


# Sample Input-3:
# ---------------
# 3 3
# a b c
# d e f
# g h i
# add

# Sample Output-3:
# ----------------
# false

n, m = list(map(int, input().split()))

arr = [input().split() for _ in range(n)]

word = input()

def dfs(arr,word,visited,i,j,curr,n,m):
    if curr == len(word):
        return True
    if i>=n or i<0 or j>=m or j<0 or visited[i][j] or arr[i][j] != word[curr]:
        return False
    
    visited[i][j] = True
    
    res = dfs(arr,word,visited,i+1,j,curr+1,n,m) or dfs(arr,word,visited,i-1,j,curr+1,n,m) or dfs(arr,word,visited,i,j+1,curr+1,n,m) or dfs(arr,word,visited,i,j-1,curr+1,n,m)
    
    visited[i][j] = False
    
    return res

def is_word_present(arr,word,n,m):
    flag = False
    for i in range(n):
        for j in range(m):
            if word[0] == arr[i][j]:
                visited = [[False]*m for _ in range(n)]
                flag = dfs(arr,word,visited,i,j,0,n,m)
    return flag

print(is_word_present(arr,word,n,m))