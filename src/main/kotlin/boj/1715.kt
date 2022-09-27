/**
 * 백준 1715번 - 카드 정렬하기
 *
 * 입력
 * 1. 숫자 카드 묶음의 수 N (1 <= N <= 100_000)
 *
 * 출력
 * 2. 숫자 카드 묶음을 합칠 때 최소 비교 횟수
 *
 * 주의사항
 * compareCount pq에 직접 넣으면 안됨. 그게 아니라 두 덱을 합친 새로운 덱을 pq에 다시 넣어주어야 함.
 */
// 반례
/*
10
123
456
234
998
12
7
876
887
455
234

답 : 12108
출력 : 31343
 */
package boj

import java.util.*

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()
    val decks = mutableListOf<Long>()

    repeat(n) {
        decks.add(reader.readLine().toLong())
    }

    val pq = PriorityQueue(decks)
    var compareCount = 0L
    while (pq.size != 1) {
        val deck = pq.remove() + pq.remove()
        compareCount += deck
        pq.add(deck)
    }

    println(compareCount)
}