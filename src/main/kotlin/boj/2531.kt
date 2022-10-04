/**
 * 백준 2531번 - 회전 초밥
 *
 * 입력
 * 1. 회전 초밥 벨트에 놓인 접시의 수(N) 초밥의 가짓수(d) 연속해서 먹는 접시의 수(k) 쿠폰번호(c)
 *    (2 <= N <= 30_000, 2 <= d <= 3_000, 2 <= k <= 3_000 (k <= N), 1 <= c <= d)
 * 2. N개의 줄에 초밥의 종류를 나타내는 정수 (1 <= <= d)
 *
 * 출력
 * 1. 주어진 회전 초밥 벨트에서 먹을 수 있는 초밥의 가짓수의 최댓값
 */
package boj

import java.lang.Integer.max

fun main() {
    val reader = System.`in`.bufferedReader()
    val (n, d, k, c) = reader.readLine().split(" ").map { it.toInt() }
    val belt = IntArray(n)

    var pos = 0
    repeat(n) {
        belt[pos++] = reader.readLine().toInt()
    }

    // 투 포인터
    var start = 0
    var end = k

    // 먹은 초밥 Set
    val eatSet = mutableSetOf<Int>()
    for (idx in 0 until end) eatSet.add(belt[idx])
    // 쿠폰을 사용해서 먹음
    eatSet.add(c)

    var maxCnt = eatSet.size

    start++
    end = start + k

    // 벨트의 모든 경우 확인
    while (start != 0) {
        eatSet.clear()

        for (idx in start until end) eatSet.add(belt[idx % n])
        eatSet.add(c)

        maxCnt = max(eatSet.size, maxCnt)

        if (++start == n) start = 0
        else end = start + k
    }

    println(maxCnt)
}