/**
 * 백준 2480번 - 주사위 세개
 *
 * 입력
 * 주사위 세 개의 나온 눈
 *
 * 출력
 * 상금
 */
package boj

import java.util.Collections.max

fun main() {
    val reader = System.`in`.bufferedReader()
    val dices = reader.readLine().split(" ").map { it.toInt() }
    var prize = 0
    val sameCounts = mutableListOf<Int>()
    var sameNum = 0

    for ((idx1, dice1) in dices.withIndex()) {

        var sameCount = 0
        for ((idx2, dice2) in dices.withIndex()) {
            if (idx1 == idx2) continue

            if (dice1 == dice2)  {
                sameCount++
                sameNum = dice2
            }
        }
        sameCounts.add(sameCount)
    }

    when (max(sameCounts) + 1) {
        3 -> prize = 10_000 + sameNum * 1_000
        2 -> prize = 1_000 + sameNum * 100
        1 -> prize = max(dices) * 100
    }

    println(prize)
}