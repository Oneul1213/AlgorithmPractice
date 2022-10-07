/**
 * 백준 2638번 - 치즈
 *
 * 입력
 * 1. NxM 모눈종이의 크기 N M (5 <= N, M <= 100)
 * 2. 모눈종이의 상태(치즈 1, 빈 공간 0. 각각 하나의 공백으로 분리)
 *
 * 출력
 * 1. 치즈가 모두 녹아 없어지는데 걸리는 정확한 시간(정수)
 */
package boj

fun main() {
    val reader = System.`in`.bufferedReader()
    val (n, m) = reader.readLine().split(" ").map { it.toInt() }
    val cheese = Array(n) { IntArray(m) }
    val inTheAir = Array(n) { BooleanArray(m) { false } }

    // cheese 초기화
    for (i in 0 until n) {
        cheese[i] = reader.readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    // 빈 공간으로 공기 이동
    moveAir(cheese, inTheAir, n, m)

    // TODO : 녹아 없어질 치즈 탐색
    
}

fun moveAir(cheese: Array<IntArray>, inTheAir: Array<BooleanArray>, n: Int, m: Int) {
    loop@ for (i in 0 until n) {
        for (j in 0 until m) {
            if (cheese[i][j] == 0) flowBfs(i, j, cheese, inTheAir)
            break@loop
        }
    }
}

fun flowBfs(startY: Int, startX: Int, cheese: Array<IntArray>, inTheAir: Array<BooleanArray>) {
    val n = cheese.size
    val m = cheese[0].size
    val directions = arrayOf(intArrayOf(0, -1), intArrayOf(-1, 0), intArrayOf(0, 1), intArrayOf(1, 0))
    val q = ArrayDeque<IntArray>()

    q.add(intArrayOf(startY, startX))
    inTheAir[startY][startX] = true
    while (q.isNotEmpty()) {
        val (y, x) = q.removeFirst()

        for (direction in directions) {
            val newY = y + direction[0]
            val newX = x + direction[1]

            if (canFlow(newY, newX, n, m, cheese)) {
                q.add(intArrayOf(newY, newX))
                inTheAir[newY][newX] = true
            }
        }
    }
}

fun canFlow(y: Int, x: Int, n: Int, m: Int, cheese: Array<IntArray>) =
    y in 0 until n && x in 0 until n && cheese[y][x] != 1