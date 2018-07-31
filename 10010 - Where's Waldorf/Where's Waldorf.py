import sys

lines = iter(sys.stdin.read().splitlines())

T = int(next(lines))

out = []

for i in range(T):
	
	for line in lines:
		num = line.split()
		if len(num) != 0:
			f = int(num[0])
			c = int(num[1])
			break
	
	mat = []
	
	for j in range(f):
		mat.append(list(next(lines).split()[0].lower()))
	
	nnames = int(next(lines))
	
	names = []
	for j in range(nnames):
		names.append(next(lines).split()[0].lower())
	
	nfound = [True]*nnames
	
	res = [""]*nnames
	
	for n,fil in enumerate(mat):
		for m,letter in enumerate(fil):
			for pos,name in enumerate(names):
				if name[0] == letter and nfound[pos]:
					for sen in [(0,1),(1,1),(1,0),(1,-1),(0,-1),(-1,-1),(-1,0),(-1,1)]:
						endf = n+(len(name)-1)*sen[0]
						endc = m+(len(name)-1)*sen[1]
						if endf >= 0 and endf <= f-1 and endc >= 0 and endc <= c-1:
							contf = 0
							contc = 0
							for let in name:
								if mat[n+contf][m+contc] == let:
									contf += sen[0]
									contc += sen[1]
								else:
									break
							else:
								res[pos] = str(n+1)+" "+str(m+1)
								nfound[pos] = False
								break
	
	out.append("\n".join(res)+"\n")

print("\n".join(out),sep="",end="")
