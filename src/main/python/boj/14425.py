# 백준 14425.py
# 
# 입력
# 집합 S에 포함된 문자열의 개수 N, 판별해야 할 문자열의 개수 M
# N개의 문자열
# ...
# M개의 문자열
# ...
import sys


si = sys.stdin.readline
n, m = map(int, si().split())
s = {}
for _ in range(n):
    s[si().strip()] = 1

in_s_count = 0
for _ in range(m):
    if s.get(si().strip()):
        in_s_count += 1

print(in_s_count)
