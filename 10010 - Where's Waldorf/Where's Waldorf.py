import sys

lines = iter(sys.stdin.read().splitlines())
N = int(next(lines))
result = []
directions = [(0,1),(1,1),(1,0),(1,-1),(0,-1),(-1,-1),(-1,0),(-1,1)]

for _ in range(N):

    next(lines)    
    rows, columns = map(int, next(lines).split())
    matrix = []
    
    for _ in range(rows):
        matrix.append(list(next(lines).split()[0].lower()))
    
    n_names = int(next(lines))  
    names = []
    for _ in range(n_names):
        names.append(next(lines).split()[0].lower())
    
    not_found = [True]*n_names  
    positions = ['']*n_names
    
    for r, row in enumerate(matrix):
        for c, item in enumerate(row):
            for i, name in enumerate(names):
                if name[0] == item and not_found[i]:
                    for h, v in directions:
                        r_end = r+(len(name)-1)*h
                        c_end = c+(len(name)-1)*v
                        if 0 <= r_end < rows and 0 <= c_end < columns:
                            r_cont = 0
                            c_cont = 0
                            for letter in name:
                                if matrix[r+r_cont][c+c_cont] == letter:
                                    r_cont += h
                                    c_cont += v
                                else:
                                    break
                            else:
                                not_found[i] = False
                                positions[i] = '%d %d\n' % (r+1, c+1)                               
                                break
    result.append(''.join(positions))

print('\n'.join(result), sep='', end='')
