'''

Write a Python code to return the length of longest sub-string without repeating 
characters

Sample Input: 
-------------
abcabcbb

Sample Output: 
--------------
3

'''

freq = set()
i = 0
j = 0

word = input()
max_size = 0
while j<len(word):
    if word[j] in freq:
        freq.remove(word[i])
        i+=1
    else:
        freq.add(word[j])
        j+=1
    max_size = max(max_size,len(freq))

print(max_size)