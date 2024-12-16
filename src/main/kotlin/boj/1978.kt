/**
 * 백준 1978번 - 소수 찾기
 * 알고리즘 분류 - 수학, 정수론, 소수 판정
 *
 * 입력
 * 수의 개수 N (N <= 100)
 * N개의 수가 공백을 기준으로 나뉘어 주어짐 (수는 1,000 이하의 자연수)
 *
 * 출력
 * 주어진 수 중 소수의 개수 출력
 */
fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val numList = readLine().split(" ").map { it.toInt() }

    var primeCnt = 0
    for (n in numList) {
        var cnt = 0
        for (i in 2..n) {
            cnt = if (n%i == 0) cnt + 1 else cnt
        }
        if (cnt == 1) primeCnt++
    }

    println(primeCnt)
}