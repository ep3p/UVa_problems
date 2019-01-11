import sys

lines = iter(sys.stdin.read().splitlines())

N = int(next(lines))
for _ in range(N):
    duration = int(next(lines))
    week = [False]*5 + [True]*2
    n_weeks, r_days = divmod(duration, 7)
    days = week*n_weeks + week[:r_days]
    weekends = 2*n_weeks + int(r_days > 5)
    
    parties = set()
    n_parties = int(next(lines))
    for _ in range(n_parties):
        parties.add(int(next(lines)))
    
    for period in parties:
        for day in range(period-1, duration, period):
            days[day] = True
    
    print(sum(days)-weekends)
