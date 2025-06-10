'''

Write a python program, for given a birthdate in yyyy-MM-dd format, calculate 
the person’s current age in years, months, and days.

Input:
------
1990-05-25

Output:
-------
Age: 35 years, 0 months, 16 days

'''

from datetime import date
from dateutil.relativedelta import relativedelta

x = list(input().split("-"))
for i in range(len(x)):
    x[i] = int(x[i])

prev = date(x[0],x[1],x[2])
today = date(2025,6,10)

age = relativedelta(today,prev)

print(f"Age: {age.years} years, {age.months} months, {age.days} days")