# Few people are playing Dart Game, where dartboard is square in shape with size,
# 100*100, bottom left corner is [0,0] and top right corner is [99,99].

# The game is played as follows:
# There are two operations in this game,
#  1- Once the player throws the dart, we will record the hit position as [x,y] 
#     in a list.
#  2- We will select one check point, and checks the number of ways to choose 
#     three points from the recorded list, such that the three points and 
#     the check point form a square with positive area

# NOTE: A edges of the square are all the same length and are either parallel 
#       or perpendicular to the left border and bottom border of the board.

# Your task is to implement the following:

# create a class "CountSquares", and the methods in it are,
#     public void record(int[] hit) : record the dart position on the board.
#     public int count(int[] check) : return the number of sqaures possible.


# Input Format:
# -------------
# Line-1: Space separated integers, Operations[], only 1's and 2's.
#         1 indiactes record the position, 2 indicates count the squares.
# Line-2: Comma separated positions, each postions is two space separated
#         integers, Dart position or Check point.

# Output Format:
# --------------
# Print the space separated integers, count of squares for each Operation-2.


# Sample Input-1:
# ---------------
# 1 1 1 2 2 1 2
# 3 10,11 2,3 2,11 10,14 8,11 2,11 10

# Sample Output-1:
# ----------------
# 1 0 2

# Explanation: 
# ------------
# Look at the hint for understanding.

class CountSquares:
    def __init__(self):
        self.points = {}
    
    def record(self, hit):
        x, y = hit
        if (x, y) in self.points:
            self.points[(x, y)] += 1
        else:
            self.points[(x, y)] = 1
    
    def count(self, check):
        cx, cy = check
        total_squares = 0
        
        for (x, y), count in self.points.items():
            if x == cx or y == cy:
                continue
            
            if abs(x - cx) != abs(y - cy):
                continue
            
            p1 = (cx, y)
            p2 = (x, cy)
            
            if p1 in self.points and p2 in self.points:
                total_squares += count * self.points[p1] * self.points[p2]
        
        return total_squares
    
operations = list(map(int, input().split()))
positions = [list(map(int, pos.split())) for pos in input().split(',')]

cs = CountSquares()
results = []
for op, pos in zip(operations, positions):
    if op == 1:
        cs.record(pos)
    elif op == 2:
        results.append(cs.count(pos))
print(' '.join(map(str, results)))