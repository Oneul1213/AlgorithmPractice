# 백준 10811 - 바구니 뒤집기
n, m = map(int, input().split())
baskets = [ i for i in range(n+1) ]

for _ in range(m):
    i, j = map(int, input().split())
    reverse_list = [ baskets[e] for e in range(i, j+1) ]
    reverse_list.reverse()
    for rl in reverse_list:
        baskets[i] = rl
        i += 1

print(" ".join(map(str, baskets[1:])), end=" ")
