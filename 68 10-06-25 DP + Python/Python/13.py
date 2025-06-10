'''

Write a python program that reads a sentence and counts how many times each word 
appears. Display only the words that occur more than once.

Input: 
------
this is a test this test is easy

Output:
-------
this -> 2
is -> 2
test -> 2

'''

from collections import Counter

sentence = input().split()

freq = Counter(sentence)

for word, count in freq.items():
    if count > 1:
        print(f"{word} -> {count}")