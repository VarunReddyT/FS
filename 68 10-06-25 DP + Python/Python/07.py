'''
Problem: 
--------
Write a Python code to identify the nth largest number without 
sorting the array


Sample Input:
-------------
4 2
3 1 5 2

Sample Output:
--------------
3

'''

import heapq

m,n = map(int,input().split())

nums = list(map(int,input().split()))


print(heapq.nlargest(n,nums)[-1])