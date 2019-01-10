import sys

pangram = 'the quick brown fox jumps over the lazy dog'

lines = iter(sys.stdin.read().splitlines())
N = int(next(lines))
next(lines)

result = []

for i in range(N):
    to_translate = []    
    for line in lines:
        if not line:
            break
        else:
            to_translate.append(line)
        
    for item in to_translate:
        phrase = ' '.join(item.split())
        if len(phrase) == len(pangram):
            table = str.maketrans(phrase, pangram)
            if phrase.translate(table) == pangram:
                result.append(''.join(map(lambda x : x.translate(table) + '\n', to_translate)))
                break
    else:
        result.append('No solution.\n')

print('\n'.join(result), sep='', end='')
