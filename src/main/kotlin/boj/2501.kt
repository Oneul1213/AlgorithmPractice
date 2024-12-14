/**
 * 백준 2501번 - 약수 구하기
 * 알고리즘 분류 - 수학, 브루트포스 알고리즘
 *
 * 입력
 * N K
 * (1 <= N <= 10,000, 1 <= K <= N)
 *
 * 출력
 * N의 약수 중 K번쨰로 작은 수. N의 약수의 개수가 K개 보다 적을 땐 0
 */
fun main() = with(System.`in`.bufferedReader()) {
    var (N, K) = readLine().split(" ").map { it.toInt() }

    var divider = 1
    var factor = 0

    while (K > 0 && divider <= N) {
        if (N % divider == 0) {
            factor = divider
            K--
        }
        divider++
    }

    if (K > 0) println(0)
    else println(factor)
}