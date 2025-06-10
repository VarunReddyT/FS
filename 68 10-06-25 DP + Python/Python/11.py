'''
Write a Python Program to read a sentence and print each word reversed, but 
maintain the original word order.

Input: 
------
Java is fun

Output:
-------
avaJ si nuf

'''

words = list(input().split())

res = ""

for i in words:
    print(i[::-1], end=" ")