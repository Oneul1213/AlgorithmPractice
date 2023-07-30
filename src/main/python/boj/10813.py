# 백준 10813번 - 공 바꾸기
n, m = map(int, input().split())
basket = [str(i) for i in range(1, n+1)]

change_list = []
for _ in range(m):
    change_list.append(map(int, input().split()))

for i in range(m):
    first, second = change_list[i]
    basket[first-1], basket[second-1] = basket[second-1], basket[first-1]

print(" ".join(basket))
