# Indus Infra Ltd purchased a land of size L * W acres, for their upcoming venture.
# The land is divided into rectangular plots, using fences. They have kept some 
# H horizontal fences as hfences[] and V vertical fences as vfences[] on the land,
# where hfence[i] is the distance from the top of the land to the i-th horizontal
# fence and, vfence[j] is the distance from the top of the land to the j-th 
# vertical fence. Each 1*1 cell is one acre plot.

# Mr.RGV wants to purchase the biggest plot available to build a Guest-house.
# Your task is to help Mr.RGV to find the biggest plot vailable after the fences 
# are setup in the venture.
# NOTE: The answer can be a large number, return the modulo of 10^9 + 7.

# Input Format:
# -------------
# Line-1: 4 space separated integers, L,W,H and V
# Line-2: H space separated integers, hfence[] in the range [0, L]
# Line-3: V space sepaarted integers, vfence[] in the range [0, W]

# Output Format:
# --------------
# Print an integer result, the area of biggest plot.


# Sample Input-1:
# ---------------
# 5 6 2 2
# 2 3
# 2 5

# Sample Output-1:
# ----------------
# 6


# Sample Input-2:
# ---------------
# 5 6 1 1
# 3
# 4

# Sample Output-2:
# ----------------
# 12

l, w, h, v = list(map(int, input().split()))
hfence = list(map(int, input().split()))
vfence = list(map(int, input().split()))

def getArea(hfence, vfence, l, w, h, v):
    hfence.sort()
    vfence.sort()
    height = hfence[0]
    breadth = vfence[0]
    for i in range(1, h):
        height = max(height, hfence[i] - hfence[i - 1])
    for i in range(1, v):
        breadth = max(breadth, vfence[i] - vfence[i - 1])
    height = max(height, l - hfence[h - 1])
    breadth = max(breadth, w - vfence[v - 1])
    return height * breadth

print(getArea(hfence, vfence, l, w, h, v))