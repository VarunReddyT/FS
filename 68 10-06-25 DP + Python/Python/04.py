# Write a python program to convert a decimal number to binary using both
# 1. Recursive method
# 2. Iterative method

# Implement the methods in Solution class and return the binary number.

# Sample Input:
# ---------------
# 10

# Sample Output:
# ------------------
# Binary (Recursive): 1010
# Binary (Iterative): 1010

class Solution:
    def binaryIterative(self,n):
        res = ""
        while(n>0):
            res = ("1" if (n%2 != 0) else "0") + res
            n = n//2
        return res
    def binaryRecursive(self,n):
        if n<= 0:
            return ""
        return self.binaryRecursive(n//2) + ("1" if (n%2 != 0) else "0")

num = int(input())

sol = Solution()

print(f"Binary (Recursive): {sol.binaryRecursive(num)}")
print(f"Binary (Iterative): {sol.binaryIterative(num)}")