# 백준 15652번 - N과 M (4)
# 
# 입력
# N M
# 
# 출력
# 1부터 N까지 자연 수 중 M개를 고른 수열(중복 허용, 비내림차순)
# -> 한 줄에 하나씩 모두 출력
import sys

def backtracking(n, m, seq):
    if len(seq) == m:
        for num in seq:
            print(num, end=' ')
        print()
        return
    
    for i in range(1, n+1):
        if len(seq) != 0 and i < seq[-1]:
            continue
        
        seq.append(i)
        backtracking(n, m, seq)
        seq.pop(-1)

si = sys.stdin.readline
n, m = map(int, si().split())
backtracking(n, m, [])