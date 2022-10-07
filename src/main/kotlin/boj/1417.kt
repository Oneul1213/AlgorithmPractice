/**
 * 백준 1417번 - 국회의원 선거
 *
 * 입력
 * 1. 후보의 수 N
 * 2. 기호 N번을 찍으려는 사람의 수
 *
 * 출력
 * 1. 매수해야하는 사람의 수의 최솟값
 */
package boj

import java.util.PriorityQueue

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()

    val pq = PriorityQueue<Int>()
    var myCnt = reader.readLine().toInt() * -1

    repeat(n-1) {
        pq.add(reader.readLine().toInt() * -1)
    }

    if (pq.size == 0) {
        println(0)
        return
    }

    var answer = 0
    var first = pq.remove()
    while (first <= myCnt) {
        answer++
        myCnt--
        first++

        pq.add(first)
        first = pq.remove()
    }

    println(answer)
}