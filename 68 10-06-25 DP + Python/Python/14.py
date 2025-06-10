'''

Write a program that reads a sentence, count and display the total number of 
vowels and consonants.

Input: 
------
Hello World

Output:
-------
Vowels: 3, Consonants: 7

'''

vowels_list = ['a','e','i','o','u']

sentence = input()

vowels = 0
consonants = 0

for char in sentence:
    if char.isalpha():
        if char.lower() in vowels_list:
            vowels += 1
        else:
            consonants += 1

print(f"Vowels: {vowels}, Consonants: {consonants}")