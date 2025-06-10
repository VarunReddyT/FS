# Write python code for a method which takes a String (a sentence) as input 
# and returns a Map. The Map key is String (word in the String) and 
# value is frequency of the word in the given sentence.
# (In the words ignore any special characters)

# Sample Input:
# -------------
# Java is fun, and Ja#va@ is powerful 

# Sample Output:
# --------------
# java: 2
# is: 2
# fun: 1
# and: 1
# powerful: 1

from collections import Counter

sentence = input()

newString = ""

for char in sentence:
    if char.isalpha():
        newString += char.lower()
    elif char == " ":
        newString += " "

words = newString.split()
word_freq = Counter(words)

for word, count in word_freq.items():
    print(f"{word}: {count}")