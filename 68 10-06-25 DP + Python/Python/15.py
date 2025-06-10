'''

Write a python program that reads two timestamps (yyyy-MM-dd HH:mm:ss format) and
display the time elapsed between them in minutes and seconds.

Input: 
------
2025-06-04 10:30:00
2025-06-04 11:15:40

Output: 
-------
Elapsed: 45 minutes 40 seconds

'''

from datetime import datetime

date1 = datetime.strptime(input(),"%Y-%m-%d %H:%M:%S")
date2 = datetime.strptime(input(),"%Y-%m-%d %H:%M:%S")

diff = date2-date1

int_seconds = int(diff.total_seconds())
minutes = int_seconds//60
seconds = int_seconds%60

print(f"Elapsed: {minutes} minutes {seconds} seconds")