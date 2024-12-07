/**
 * 백준 11005번 - 진법 변환2
 * 알고리즘 분류 - 수학, 구현
 *
 * 입력
 * 10진법 수 N과 B진법이 주어질 때,
 * 1. N B
 * (2 <= B <= 36),B진법 수 N은 10억보다 작거나 같은 자연수
 *
 * 출력
 * 10진법 수 N을 B진법으로 출력
 */
fun main() = with(System.`in`.bufferedReader()) {
    val (N, B) = readLine().split(" ")

    val numMap = mutableMapOf<Int, String>()
    for (i in 0..9) {
        numMap[i] = i.toString()
    }
    var c = 'A'
    for (i in 10..35) {
        numMap[i] = (c++).toString()
    }

    var n = N.toInt()
    val b = B.toInt()

    var baseB = ""
    while (n >= b) {
        baseB += numMap[n % b]
        n /= b
    }
    if (n > 0) baseB += numMap[n]
    println(baseB.reversed())
}