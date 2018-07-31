import sys

lines = iter(sys.stdin.read().splitlines())

T = int(next(lines))

for i in range(T):

	for line in lines:
		if line.isdigit():
			C = int(line)
			break
	
	cand = []
	for j in range(C):
		cand.append(next(lines))
	
	votes = []
	for j,line in enumerate(lines):
		num = line.split()
		if len(num) == 0:
			if line == "":
				break
		else:
			if j < 1000:
				votes.append(list(map(lambda x : int(x)-1 ,num)))
	

	
	pres = [True]*C
	cont = [0]*C
	
	for vote in votes:
		cont[vote[0]] += 1
	
	while 1:
		minimo = min([c for j,c in enumerate(cont) if pres[j] ])
		maximo = max(cont)
		if minimo == maximo or maximo > len(votes)/2.0:
			for j,c in enumerate(cont):
				if pres[j] and c != maximo:
					pres[j] = False
			break
			
		else:
			for j,c in enumerate(cont):
				if pres[j] and c == minimo:
					cont[j] = 0
					pres[j] = False
			
			for j,vote in enumerate(votes):
				supr = 0
				guar = -1
				for vot in vote:
					if pres[vot]:
						guar = vot
						break
					
					else:
						supr += 1
						
				if supr > 0 and guar != -1:
					cont[guar] += 1
					for z in range(supr):
						votes[j].pop(0)
	
	if i == T-1:				
		print("\n".join([cand[j] for j,p in enumerate(pres) if p]),sep="",end = "\n")
	else:
		print("\n".join([cand[j] for j,p in enumerate(pres) if p]),"\n",sep="")
