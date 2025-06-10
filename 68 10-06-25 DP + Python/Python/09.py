'''

Write python program to find and return minimum number of denominations – given 
an infinite supply of each denomination of {1, 2, 5, 10, 20, 50, 100, 200,500, 
2000} and a target value N

Sample Input: 
-------------
187

Sample Output: 
--------------
[100, 50, 20, 10, 5, 2]

'''

n = int(input())
res = []
while n>0:
    if n>=2000:
        res.append(2000)
        n -= 2000
    elif n>=500:
        res.append(500)
        n -= 500
    elif n>=200:
        res.append(200)
        n -= 200
    elif n>=100:
        res.append(100)
        n -= 100
    elif n>=50:
        res.append(50)
        n -= 50
    elif n>=20:
        res.append(20)
        n -= 20
    elif n>=10:
        res.append(10)
        n -= 10
    elif n>=5:
        res.append(5)
        n -= 5
    elif n>=2:
        res.append(2)
        n -= 2
    elif n>=1:
        res.append(1)
        n -= 1
print(res)