T = int(input())

for i in range(T):
	
	D = int(input())
	P = int(input())
	
	q,r = divmod(D,7)
	w = [True,True,True,True,True,False,False]
	w = w*q + w[:r]
	f = [0]*(7*q+r)
	
	p = set()
	
	for j in range(P):
		p.add(int(input()))
	
	for j in p:
		for z in range(j-1,D,j):
			if w[z]:
				f[z] = 1
	
	print(sum(f),sep="")
