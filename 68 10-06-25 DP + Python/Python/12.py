'''

Write a program that finds the longest substring that reads the same forwards 
and backwards.

Input: 
------
babad

Output: 
-------
bab or aba

'''

word = input()

def palindrome(x):
    return x==x[::-1]
res = ""
length = 0

for i in range(len(word)):
    for j in range(1,len(word)+1):
        if(palindrome(word[i:j]) and len(word[i:j])>length):
            res = word[i:j]
            length = len(word[i:j])
            
print(res)