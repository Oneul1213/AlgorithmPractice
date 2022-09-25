/**
 * 백준 1715번 : 카드 정렬하기
 *
 * 입력
 * 1. 숫자 카드 묶음의 수 N (1 <= N <= 100_000)
 *
 * 출력
 * 2. 숫자 카드 묶음을 합칠 때 최소 비교 횟수
 */
// TODO : 8% 틀렸습니다 해결 (작은 것 부터 고르는 방식 검증. 생각해보기)
package boj

import java.util.*

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()
    val decks = mutableListOf<Int>()

    repeat(n) {
        decks.add(reader.readLine().toInt())
    }

    val pq = PriorityQueue(decks)
    var compareCount = 0
    while (pq.size != 1) {
        compareCount += pq.remove() + pq.remove()
        pq.add(compareCount)
    }

    println(pq.remove())
}