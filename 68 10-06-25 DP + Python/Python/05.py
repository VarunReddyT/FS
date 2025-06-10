'''
Problem: Write a program to count each word's first index and total occurrences 
in a sentence.

Sample Input: 
apple banana apple orange banana apple

Sample Output:
apple -> first index: 0, count: 3
banana -> first index: 6, count: 2
orange -> first index: 19, count: 1

'''

from collections import Counter

sentence = input()

sent = sentence.split()

word_freq = Counter(sent)

for word, count in word_freq.items():
    print(f"{word} -> first index: {sentence.find(word)}, count: {count}")