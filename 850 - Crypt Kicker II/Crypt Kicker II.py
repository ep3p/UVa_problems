import sys

lines = iter(sys.stdin.read().splitlines())

T = int(next(lines))
next(lines)

out = []

for i in range(T):
	
	sent = []
	j = 0
	
	for line in lines:
		num = line.split()
		if len(num) == 0:
			if line == "":
				break
		else:
			if j < 100:
				sent.append(line)
			j += 1
	
	dict = None
	
	for s in sent:
		words = s.split()
		if 	len(words) == 9 and \
			len(words[0]) == 3 and \
			len(words[1]) == 5 and \
			len(words[2]) == 5 and \
			len(words[3]) == 3 and \
			len(words[4]) == 5 and \
			len(words[5]) == 4 and \
			len(words[6]) == 3 and \
			len(words[7]) == 4 and \
			len(words[8]) == 3 and \
			words[0] == words[6] and \
			words[0][2] == words[5][2] and \
			words[1][1] == words[4][1] and \
			words[2][1] == words[5][3] and \
			words[2][2] == words[3][1] and \
			words[2][2] == words[5][0] and \
			words[2][2] == words[8][1]:
			dict = "".maketrans(" ".join(words),"the quick brown fox jumps over the lazy dog")
			if " ".join(words).translate(dict) != "the quick brown fox jumps over the lazy dog":
				dict = None
			else:
				break

	if dict is not None:
		out.append("\n".join(map(lambda x : x.translate(dict),sent))+"\n")
	else:
		out.append("No solution.\n")

print("\n".join(out),sep="",end="")
