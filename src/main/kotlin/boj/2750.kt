/**
 * 백준 2750번 - 수 정렬하기
 *
 * 입력
 * 1. 수의 개수 N (1 <= N <= 1_000)
 * 2. N개의 줄에 절댓값이 1_000보다 작거나 같은 정수. 수는 중복되지 않음
 *
 */
package boj

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()

    val numbers = mutableListOf<Int>()
    repeat(n) { numbers.add(reader.readLine().toInt()) }

    numbers.sorted().forEach { println(it) }
}