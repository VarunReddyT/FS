'''
In a hierarchical research institute, every scientist reports to a supervising 
scientist. Given two scientists, find the nearest common supervisor (LCS) who 
oversees both (directly or indirectly).

                Director
                /      \
         Dr.Alpha       Dr.Delta
         /     \          /     \
   Dr.Beta   Dr.Gamma  Dr.Epsilon  Dr.Zeta

Input Format
------------
You’ll be given:
    - A set of parent–child relationships defining the hierarchy.
    - Two scientist names whose nearest common supervisor needs to be found.

Output Format:
--------------
A string, Name of LCS

Sample Input:
-------------
{"Director": None, "Dr.Alpha": "Director", "Dr.Delta": "Director", "Dr.Beta": "Dr.Alpha", "Dr.Gamma": "Dr.Alpha", "Dr.Epsilon": "Dr.Delta", "Dr.Zeta": "Dr.Delta"}
("Dr.Beta", "Dr.Gamma")

Sample Output:
--------------
Dr.Alpha

'''

'''
In a hierarchical research institute, every scientist reports to a supervising 
scientist. Given two scientists, find the nearest common supervisor (LCS) who 
oversees both (directly or indirectly).

                Director
                /      \
         Dr.Alpha       Dr.Delta
         /     \          /     \
   Dr.Beta   Dr.Gamma  Dr.Epsilon  Dr.Zeta

Input Format
------------
You’ll be given:
    - A set of parent–child relationships defining the hierarchy.
    - Two scientist names whose nearest common supervisor needs to be found.

Output Format:
--------------
A string, Name of LCS

Sample Input:
-------------
{"Director": None, "Dr.Alpha": "Director", "Dr.Delta": "Director", "Dr.Beta": "Dr.Alpha", "Dr.Gamma": "Dr.Alpha", "Dr.Epsilon": "Dr.Delta", "Dr.Zeta": "Dr.Delta"}
("Dr.Beta", "Dr.Gamma")

Sample Output:
--------------
Dr.Alpha

'''
import json

class Node:
    def __init__(self,value):
        self.value = value
        self.left = None
        self.right = None

def build_tree(hierarchy):
    nodes = {}
    root = None
    for child, parent in hierarchy.items():
        if child not in nodes:
            nodes[child] = Node(child)
        child_node = nodes[child]
        
        if parent is None:
            root = child_node
        else:
            parent_node = nodes[parent]
            
            if parent_node.left is None:
                parent_node.left = child_node
            else:
                parent_node.right = child_node
    return root
def lca(root, a, b):
    if root is None or root.value == a or root.value == b:
        return root
    left = lca(root.left,a,b)
    right = lca(root.right,a,b)
    if left and right:
        return root
    if left is None:
        return right
    return left

hierarchy = eval(input())

name1, name2 = eval(input())

root = build_tree(hierarchy)

print(lca(root,name1,name2).value)