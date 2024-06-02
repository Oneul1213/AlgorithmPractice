# 백준 2444번 - 별 찍기
#
# 입력
# N (1 <= N <= 100)
#
# 출력
# 첫쨰 줄부터 2xN-1번째 줄까지 규칙에 맞는 별 출력
import sys

si = sys.stdin.readline
N = int(si())

def print_star(space, star):
    print(" "*space, end='')
    print("*"*star, end='')

space = ((2*N-1) // 2) + 1
star = -1

for i in range(1, 2*N):
    if (i <= (2*N-1) // 2 + 1):
        space -= 1
        star += 2
        print_star(space, star)
        print()
    else:
        space += 1
        star -= 2
        print_star(space, star)
        if (i != 2*N-1):
            print()
