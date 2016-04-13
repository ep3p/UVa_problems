import sys

lines = iter(sys.stdin.read().splitlines())

for line in lines:
	m = int(line)
	cont = m
	r = 0
	while cont != 0:
		if cont % 10 == 1:
			r += 1
			cont //= 10
		else:
			cont += m
			
	print (r,sep="")
