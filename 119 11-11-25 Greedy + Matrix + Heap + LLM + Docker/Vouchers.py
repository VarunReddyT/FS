# Suresh is an employee in ABC company. He can avail N vouchers each day except 
# Thursday. He uses the vouchers for his daily needs. He needs M voucher per day.

# Suresh will work W number of days in ABC company, He has to lead his life using 
# the vouchers only.
# You are given three integers, W, N and M, where
#     W – Number of days you are required to work.
# 	N – Maximum number of vouchers you can avail each day.
# 	M – Number of vouchers required each day to live.

# Currently, it’s Friday, and Suresh need to work for the next W days.

# Your task is to find the minimum number of times, Suresh avails
# the vouchers from the company, so that he can live and work for next W days, or 
# determine that it is not possible to work W days. If possible, 
# print "true and an integer", minimum number of times he avails the vouchers
# else, print "false".

# Note: If Suresh doesn't enough vouchers to spend for his living, He will not work.


# Input Format:
# -------------
# Three integers, W, N and M.

# Output Format:
# --------------
# Print the result, as shown in the sample testacses.


# Sample Input-1:
# ---------------
# 10 16 2

# Sample Output-1:
# ----------------
# true 2

# Explanation:
# ------------
# One possible solution is to avail 16 vouchers on the first day (Friday),
# it’s sufficient to live using 16 vouchers up to 8th day (Friday) inclusive. 
# Now, on the 9th day (Saturday), you can avail another 16 vouchers and use them 
# to live and work on 9th and 10th day.


# Sample Input-2:
# ----------------
# 10 20 30

# Sample Output-2:
# -----------------
# false

# Explanation: 
# ------------
# You can’t work as you don't have enouh vouchers for living even if you avail 
# 20 vouchers per day, because the maximum number of vouchers reuired are 30 per day.


w, n, m = list(map(int, input().split()))

def isPossible(w,n,m):
    if n<m:
        print("false")
        return

    count = 0
    currVouchers = 0
    
    for i in range(w):
        currDay = i%7
        if currDay == 5 and currVouchers < m * 2:
            if currVouchers + n < m * 2:
                print("false")
                return
            currVouchers += n
            count += 1
        if currVouchers < m:
            if currDay == 6:
                print("false")
                return
            currVouchers += n
            count += 1
        currVouchers -= m
    
    print(f"true {count}")

isPossible(w,n,m)