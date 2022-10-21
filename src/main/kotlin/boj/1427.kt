/**
 * 백준 1427번 - 소트인사이드
 *
 * 입력
 * 1. 정렬하려는 수 N (N <= 1_000_000_000)
 *
 * 출력
 * 1. 내림차순으로 자릿수가 정렬된 수
 */
package boj

fun main() {
    val reader = System.`in`.bufferedReader()
    val listedNumber = reader.readLine().toList()
    println(listedNumber.sorted().reversed().joinToString("").toInt())
}