import sys
lines = iter(sys.stdin.read().splitlines())

steps = []
product = 1
while product < 4294967295:
    product *= 9
    steps.append(product)
    product *= 2
    steps.append(product)

for line in lines:	
    objective = int(line)
    for i, n in enumerate(steps):
        if n >= objective:
	        break
    if i % 2 == 0:
        result = 'Stan wins.'
    else:
        result = 'Ollie wins.'
    print(result, sep = '', end = '\n')
