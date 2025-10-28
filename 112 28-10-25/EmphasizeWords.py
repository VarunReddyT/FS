# Reena has to sent the mails regularly.
# She used emphasize any word W based on a given set of words[].
# Two emphasize the words she used enclose them with <i> and </i> tag.
# The emphasize procedure of the sub-words of W is as follows:
	
# 	- If two sub-words are intersected with each other, 
# 	  enclose them with one <i></i> tag.
	
# 	- If two sub-words are enclosed with <i></i> tag and neighbours also,
# 	  then merge the sub-words as one and enclose with one <i> </i> tag.

# You will be given the word W, and set of words[] to emphasize.
# Your task is to help Reena to emphasize the word W.
# and print it.

# Input Format:
# -------------
# Line-1: A string W, the word.
# Line-2: space separated strings, set of words[].

# Output Format:
# --------------
# Print the string, the emphasized string.

# Sample Input-1:
# ---------------
# kmitngit123
# it 123

# Sample Output-1:
# ----------------
# km<i>it</i>ng<i>it123</i>


# Sample Input-2:
# ---------------
# qwertykeypad
# qwerty key pad

# Sample Output-2:
# ----------------
# <i>qwertykeypad</i>


word = input()
words = input().split()

def emphasizeWords(word,words):
    n = len(word)
    arr = [False]*n
    res = ""
    for x in words:
        currIdx = 0
        while True:
            idx = word.find(x,currIdx)
            if idx == -1:
                break
            for i in range(idx, idx + len(x)):
                arr[i] = True
            currIdx = idx + 1
    i = 0
    while i<n:
        if arr[i]:
            res += "<i>"
            while i<n and arr[i]:
                res += word[i]
                i += 1
            res += "</i>"
        else:
            res += word[i]
            i += 1
    return res
    
print(emphasizeWords(word,words))
    
