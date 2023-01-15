# 백준 1081번 - 숫자 카드
#
# 입력
# 상근이가 가지고 있는 숫자 카드의 수  N
# N 개의 정수
# 상근이가 가지고 있는지 알아내야 할 카드의 수 M
# M 개의 정수
# 
# 출력
# M개의 1 또는 0. 상근이가 가지고 있으면 1, 아니면 0
import sys

si = sys.stdin.readline
n = int(si())
have = map(int, si().split())
have_map = { num:1 for num in have }
m = int(si())
is_have = map(int, si().split())

for num in is_have:
    print(have_map.get(num, 0), end=' ')
