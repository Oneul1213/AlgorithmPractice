# 백준 10810번 - 공 넣기

n, m = map(int, input().split())
result = [0] * n
for _ in range(m):
    start, end, ball = map(int, input().split())
    for i in range(start, end + 1):
        result[i-1] = ball

print(*result)