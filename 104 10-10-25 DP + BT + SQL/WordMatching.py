# Given two strings S1 and S2, find if S2 can match S1 or not.

# A match that is both one-to-one (an injection) and onto (a surjection), 
# i.e. a function which relates each letter in string S1 to a separate and 
# distinct non-empty substring in S2, where each non-empty substring in S2
# also has a corresponding letter in S1.

# Return true,if S2 can match S1.
# Otherwise false.

# Input Format:
# -------------
# Line-1 -> Two strings S1 and S2

# Output Format:
# --------------
# Print a boolean value as result.


# Sample Input-1:
# ---------------
# abab kmitngitkmitngit

# Sample Output-1:
# ----------------
# true


# Sample Input-2:
# ---------------
# aaaa kmjckmjckmjckmjc

# Sample Output-2:
# ----------------
# true

# Sample Input-3:
# ---------------
# mmnn pqrxyzpqrxyz

# Sample Output-3:
# ----------------
# false

def backtrack(s1, s2, index1, index2, mapping, used):
    if index1 == len(s1) and index2 == len(s2):
        return True
    if index1 == len(s1) or index2 == len(s2):
        return False

    c = s1[index1]

    if c in mapping:
        curr = mapping[c]
        if not s2.startswith(curr, index2):
            return False
        return backtrack(s1, s2, index1 + 1, index2 + len(curr), mapping, used)

    for i in range(index2 + 1, len(s2) + 1):
        sub = s2[index2:i]
        if sub in used:
            continue
        mapping[c] = sub
        used.add(sub)
        if backtrack(s1, s2, index1 + 1, i, mapping, used):
            return True
        del mapping[c]
        used.remove(sub)

    return False

def is_matching(s1, s2):
    return backtrack(s1, s2, 0, 0, {}, set())

# Main
s1, s2 = input().split()
print(is_matching(s1, s2))
