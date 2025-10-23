# A manufacturing system includes multiple automated stages.
# Some stages can only begin after others are finished.

# The goal is to determine a valid sequence of stage execution that satisfies all
# dependencies.

# Problem Statement
# -----------------
# Given the dependencies among production stages, determine a valid order in
# which all stages can be executed.

# Input Format
# ------------
# Dictionary where keys = stage names, values = list of prerequisite stages

# Output Format
# -------------
# valid order of stage names as a list.


# Sample Input:
# -------------
# { "Assembly": [], "QualityCheck": ["Assembly"], "Packaging": ["Assembly"], "Dispatch": ["QualityCheck", "Packaging"] }

# Sample Output:
# --------------
# ["Assembly", "QualityCheck", "Packaging", "Dispatch"]


map : dict[str,list[str]] = eval(input())

valid_map : dict[str,list[str]] = {}
indeg : dict[str,int]= {}

for key, value in map.items():
    if indeg.get(key) is None:
        indeg[key] = 0
    for node in value:
        if valid_map.get(node) is None:
            valid_map[node] = []
        if indeg.get(node) is None:
            indeg[node] = 0
        valid_map[node].append(key)
    indeg[key] += len(value)


q : list[str] = []
res : list[str]= []

for k,v in indeg.items():
    if v==0:
        q.append(k)

while len(q)!=0:
    top : str = q.pop(0)
    res.append(top)
    if valid_map.get(top) is None : 
        continue
    for node in valid_map.get(top):  # pyright: ignore[reportOptionalIterable]
        indeg[node] -=1
        if(indeg[node]==0):
            q.append(node)

print(res)