import sys

def fmt_print(buf):
    if len(buf):
        print((" ".join(buf)).rstrip(), sep="", end="\n")

lines = iter(sys.stdin.read().splitlines())

buf = []
n = -1

for line in lines:
    if not line:
        fmt_print(buf)
        print("\n", sep="", end="")
        buf = []
        n = -1
    else:
        if line[0] == ' ':
            fmt_print(buf)
            buf = []
            n = -1
        sep = ' '
        rest = line
        while sep:
            bef, sep, rest = rest.partition(' ')
            if n != 72 or bef:
                if n + 1 + len(bef) > 72:
                    fmt_print(buf)
                    buf = [bef]
                    n = len(bef)
                else:
                    buf.append(bef)
                    n += 1 + len(bef)
fmt_print(buf)
