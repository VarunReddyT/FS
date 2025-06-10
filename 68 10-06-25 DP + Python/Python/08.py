'''

Problem: 
Write Python code to identify if given two strings are Anagrams 
(strings that contain same set of alphabets)

Sample Input: 
-------------
listen silent

Sample Output: 
--------------
true

'''

from collections import Counter

word1, word2 = input().split()

freq1 = Counter(word1)

freq2 = Counter(word2)

print("true" if (freq1==freq2) else "false")
