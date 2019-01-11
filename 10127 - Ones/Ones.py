import sys

lines = iter(sys.stdin.read().splitlines())

for line in lines:
    factor = int(line)
    product = factor
    length = 0
    while product:
        if product % 10 == 1:
            length += 1
            product //= 10
        else:
            product += factor
    print(length)
