/**
 * 백준 1309번 - 동물원
 *
 * 입력
 * 1. 2*N 크기의 우리의 길이 N(1 <= N <= 100_000)
 *
 * 출력
 * 1. 사자를 우리에 배치하는 경우의 수를 9901로 나눈 나머지
 *
 * dp[i][x] = 길이 i 자리에 x 형태로 배치하는 경우의 수
 *
 * x == 0 : 이번 우리에 사자를 배치하지 않음
 * x == 1 : 왼쪽에 사자를 배치
 * x == 2 : 오른쪽에 사자를 배치
 *
 * 점화식
 * dp[i][0] = dp[i-1][0] + dp[i-1][1] + dp[i-1][2]
 * dp[i][1] = dp[i-1][0] + dp[i-1][2]
 * dp[i][2] = dp[i-1][0] + dp[i-1][1]
 */
package boj

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()

    val dp = Array(n+1) { IntArray(3) { -1 } }

    // dp 초기화
    dp[1][0] = 1
    dp[1][1] = 1
    dp[1][2] = 1

    // 점화식에 따라 반복문 돌기
    for (i in 2..n) {
        dp[i][0] = dp[i-1][0] + dp[i-1][1] + dp[i-1][2]
        dp[i][1] = dp[i-1][0] + dp[i-1][2]
        dp[i][2] = dp[i-1][0] + dp[i-1][1]
    }

    println((dp[n][0] + dp[n][1] + dp[n][2]) % 9901)
}