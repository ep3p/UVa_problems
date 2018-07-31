import sys
from collections import Counter

lines = iter(sys.stdin.read().splitlines())

for line in lines:
	
	r = ""
	rank = [0,0]
	numbers = []
	colors = []
	
	dict = {ord("T"): "10", ord("J"): "11", ord("Q"): "12", ord("K"): "13", ord("A"): "14"}
	
	for card in line.split():
		numbers.append(int(card[0].translate(dict)))
		colors.append(card[1])
		
	numbers = [sorted(Counter(numbers[0:5]).items(), key = lambda x: x[1], reverse = True),sorted(Counter(numbers[5:]).items(),key = lambda x: x[1], reverse = True)]
	colors = [colors[0:5],colors[5:]]
	
	high = False
	
	for i in range(2):
		#all same color
		if len(set(colors[i])) == 1:
			#straight
			if numbers[i][0][1] == 1 and max(numbers[i])[0] - min(numbers[i])[0] == 4:
				rank[i] += 800 + max(numbers[i])[0]
				
			#flush
			else:
				rank[i] += 500
				high = True
		
		#different color		
		else:
			#poker
			if numbers[i][0][1] == 4:
				rank[i] += 700 + numbers[i][0][0]
			
			elif numbers[i][0][1] == 3:
				#full house
				if numbers[i][1][1] == 2:
					rank[i] += 600
				
				#3 kind	
				else:
					rank[i] += 300
					
				rank[i] += numbers[i][0][0]
			
			elif numbers[i][0][1] == 2:
				#2 pairs
				if numbers[i][1][1] == 2:
					rank[i] += 200 + max(numbers[i][0][0],numbers[i][1][0]) + min(numbers[i][0][0],numbers[i][1][0])/14.0
				
				#pair	
				else:
					rank[i] += 100 + numbers[i][0][0]
				high = True
			
			#straight
			elif max(numbers[i])[0] - min(numbers[i])[0] == 4:
				rank[i] += 400 + max(numbers[i])[0]
				
			#high card
			else:
				high = True
					
	if rank[0] == rank[1] and high:
		high = [sorted([n for n,o in numbers[0] if o == 1], reverse = True),sorted([n for n,o in numbers[1] if o == 1], reverse = True)]
		
		for i in range(len(high[0])):
			if high[0][i] != high[1][i]:
				rank[0] += high[0][i]
				rank[1] += high[1][i]
				break
	
	if rank[0] > rank[1]:
		r = "Black wins."
	elif rank[0] == rank[1]:
		r = "Tie."
	else:
		r = "White wins."
	
	print(r,sep="")
