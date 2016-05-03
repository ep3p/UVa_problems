import sys

lines = iter(sys.stdin.read().splitlines())

cont = 1

sear = []

while cont < 4294967295:
	cont *= 9
	sear.append(cont)
	
	cont *= 2
	sear.append(cont)

for line in lines:
	
	for i,n in enumerate(sear):
		if int(line) <= n:
			r = i
			break

	if r % 2 == 0:
		res = "Stan wins."
	else:
		res = "Ollie wins."
	
	print(res, sep = "", end = "\n")
