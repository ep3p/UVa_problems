import sys

def prent(cont):
	if len(cont) != 0:
		print((" ".join(cont)).rstrip(), sep="", end="\n")


lines = iter(sys.stdin.read().splitlines())

n = -1
cont = []

for line in lines:
	if line == "":
		prent(cont)
		cont = []
		n = -1
		print("\n", sep="", end="")
		
	else:
		if line[0] == " ":
			prent(cont)
			cont = []
			n = -1
		sep = " "
		rest = line
		while sep != "":
			bef,sep,rest = rest.partition(" ")
			if n == 72 and bef == "":
				pass
			
			elif n + 1 + len(bef) > 72:
				prent(cont)
				cont = [bef]
				n = len(bef)
			else:
				cont.append(bef)
				n += 1 + len(bef)
prent(cont)
