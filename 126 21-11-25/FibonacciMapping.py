# Every letter from A to Z is mapped to a number in the Fibonacci sequence, 
# starting from the 10th Fibonacci number.
# A → 10th Fibonacci number (55)
# B → 11th Fibonacci number (89)
# …
# Z → 35th Fibonacci number

# Assume the standard Fibonacci sequence: F₀ = 0, F₁ = 1, F₂ = 1, F₃ = 2, …

# Given a word (only letters A–Z / a–z), map each letter to its Fibonacci value 
# and compute the sum of all mapped values. Then repeatedly apply the Absolute 
# Alternating Sum rule to this sum until you get a single digit.

# Absolute Alternating Sum Rule:
# Take the digits of the number and subtract/add alternately, starting 
# with subtraction on the second digit (equivalently the formula):
# ∣ 𝑑1−𝑑2+𝑑3−𝑑4+…∣ where 𝑑1,𝑑2,… are the digits of the number 
# from left to right. Take the absolute value of the result.

# If the result has more than one digit, repeat the same rule again on this result.
# Stop when the result is a single digit (0–9). Print that digit.
# You may assume input is a single word.

# Input Format:
# -------------
# A String

# Output Format:
# --------------
# An integer 


# Sample Input:
# -------------
# AA

# Sample Output:
# ---------------
# 0

# Sample Input:
# -------------
# Z

# Sample Output:
# ---------------
# 5