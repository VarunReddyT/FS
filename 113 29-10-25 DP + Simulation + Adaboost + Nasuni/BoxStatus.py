'''

In a room there are 8 boxes in a row and each box is either locked or unlocked.
In each step, status of the boxes, modifies according to the following rules:
	- if the adjacent boxes of the box[i] (i.e., box[i-1] and box[i+1] ) are 
	  either both locked or both unlocked, then the box[i] becomes locked.
	- Otherwise box[i] is unlocked.
	- The first and the last boxes in the row can't have two adjacent boxes.

You are given the initial status of 8-boxes status[] array and an integer S, 
consist of either 0 or 1. where 1 indiactes locked status, 
0 indiactes unlocked status, and S is the number of steps.

Your task is to find and return the final status of the boxes after S steps,
either locked or unlocked.


Input Format:
-------------
Line-1: Eight space separated integers, initial status of the 8-boxes, 0 or 1 only
Line-2: An integer S, numebr of steps.

Output Format:
--------------
Print the list of integers, final status of the 8-boxes.


Sample Input-1:
---------------
1 1 0 0 1 0 0 1
6

Sample Output-1:
----------------
[0, 1, 0, 1, 1, 1, 1, 0]

Explanation:
------------
Initial status is Step-0:
Step-0: [1, 1, 0, 0, 1, 0, 0, 1]
Step-1: [0, 0, 0, 0, 1, 0, 0, 0]
Step-2: [0, 1, 1, 0, 1, 0, 1, 0]
Step-3: [0, 0, 0, 1, 1, 1, 1, 0]
Step-4: [0, 1, 0, 0, 1, 1, 0, 0]
Step-5: [0, 1, 0, 0, 0, 0, 0, 0]
Step-6: [0, 1, 0, 1, 1, 1, 1, 0]
Final status is Step-6.


Sample Input-2:
---------------
1 0 0 1 0 1 1 0
8

Sample Output-2:
----------------
[0, 0, 0, 1, 1, 0, 0, 0]

'''

status = list(map(int, input().split()))
s = int(input())
n = len(status)
seen = {}

while s > 0:
    curr = tuple(status)
    if curr in seen :
        cycle = seen[curr] - s
        s %= cycle
        if s == 0 :
            break
    else:
        seen[curr] = s
    s -= 1
    orig = status[:]
    temp = [0]*8
    for i in range(1,7):
        if orig[i-1] == orig[i+1]:
            temp[i] = 1
        else:
            temp[i] = 0
    status = temp


print(status)
        