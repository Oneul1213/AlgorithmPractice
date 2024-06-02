# 백준 9086번 - 문자열
# 
# 입력
# 테스트 케이스 개수 T (1 <= T <= 10)
# A~Z 로 이루어진 한 줄의 문자열 T개 (문자열 길이 1~1000)
# 
# 
# 출력
# 각 문자열의 첫 글자, 마지막 글자 연속 출력
import sys

si = sys.stdin.readline
T = si()
for i in range(int(T)):
    string = si().strip()
    print(string[0], end='')
    print(string[-1])
