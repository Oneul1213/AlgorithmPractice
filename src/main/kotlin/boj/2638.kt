/**
 * 백준 2638번 - 치즈
 *
 * 입력
 * 1. NxM 모눈종이의 크기 N M (5 <= N, M <= 100)
 * 2. 모눈종이의 상태(치즈 1, 빈 공간 0. 각각 하나의 공백으로 분리)
 *
 * 출력
 * 1. 치즈가 모두 녹아 없어지는데 걸리는 정확한 시간(정수)
 *
 * 주의사항
 * - 한 번의 dfs 탐색에서 inTheAir 를 매번 초기화시켜주지 않으면 0, 0에서 시작한
 *   flowBfs 탐색이 주변의 inTheAir = true 에 막혀서 inTheAir 의 변화가 생기지 않음.
 *
 * 문제가 생겼던 부분
 * 1. 무한 루프(inTheAir 가 매번 초기화되지 않아서 inTheAir 의 변화가 없어 탐색이 끝나지 않음)
 * 2. 2중 for 문을 사용해서 첫 0과 inTheAir = false 인 위치에서 flowBfs 를 해주면
 *    치즈 내부의 공간도 대상이 될 수 있음.
 *    -> 0, 0 부터 공기의 흐름을 시작하게 하고, inTheAir 를 탐색 전에 초기화시켜서 해결
 *
 * 반례
8 9
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 1 1 0 0 0 1 1 0
0 1 0 1 1 1 0 1 0
0 1 0 0 1 0 0 1 0
0 1 0 1 1 1 0 1 0
0 1 1 0 0 0 1 1 0
0 0 0 0 0 0 0 0 0
 * 정답 : 3
 * 출력 : 2
 */
package boj

fun main() {
    val reader = System.`in`.bufferedReader()
    val (n, m) = reader.readLine().split(" ").map { it.toInt() }
    val cheese = Array(n) { IntArray(m) }
    val inTheAir = Array(n) { BooleanArray(m) { false } }
    var time = 0

    // cheese 배치
    for (i in 0 until n) {
        cheese[i] = reader.readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    while (cheese.any { it.contains(1) }) {
        // 빈 공간으로 공기 이동
        moveAir(cheese, inTheAir, n, m)

        // 삭제 예정 큐(삭제 예정 위치의 y, x 저장)
        val meltingQ = ArrayDeque<Pair<Int, Int>>()

        // 치즈 녹음
        melt(n, m, cheese, inTheAir, meltingQ)
        while (meltingQ.isNotEmpty()) {
            val (y, x) = meltingQ.removeFirst()
            cheese[y][x] = 0
        }

        // 시간이 흐름
        time++
    }

    println(time)
}

// 공기를 흘러야하는 위치로 흘림
fun moveAir(cheese: Array<IntArray>, inTheAir: Array<BooleanArray>, n: Int, m: Int) {
    initInTheAir(inTheAir, n, m)
    flowBfs(0, 0, cheese, inTheAir)
}

// inTheAir 초기화(전부 false. 메모리 대신 시간 사용)
fun initInTheAir(inTheAir: Array<BooleanArray>, n: Int, m: Int) {
    for (y in 0 until n) {
        for (x in 0 until m) {
            inTheAir[y][x] = false
        }
    }
}

// 공기가 흐를 위치 탐색
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

            if (canFlow(newY, newX, n, m, cheese, inTheAir)) {
                q.add(intArrayOf(newY, newX))
                inTheAir[newY][newX] = true
            }
        }
    }
}

// 격자를 벗어나지 않았는지 확인
fun inGrid(y: Int, x: Int, n: Int, m: Int) = y in 0 until n && x in 0 until m

// 공기가 흐를 수 있는 곳인지 확인
fun canFlow(y: Int, x: Int, n: Int, m: Int, cheese: Array<IntArray>, inTheAir: Array<BooleanArray>) =
    inGrid(y, x, n, m) && cheese[y][x] != 1 && !inTheAir[y][x]

// 녹아야할 치즈 위치 표시
fun melt(n: Int, m: Int, cheese: Array<IntArray>, inTheAir: Array<BooleanArray>, meltingQ: ArrayDeque<Pair<Int, Int>>) {
    for (y in 0 until n) {
        for (x in 0 until m) {
            if (cheese[y][x] == 1) {
                if (canMelt(y, x, n, m, inTheAir)) meltingQ.add(Pair(y, x))
            }
        }
    }
}

// 이 칸의 치즈가 녹을 수 있는지 확인
fun canMelt(y: Int, x: Int, n: Int, m: Int, inTheAir: Array<BooleanArray>): Boolean {
    val directions = arrayOf(
        intArrayOf(0, -1), intArrayOf(-1, 0), intArrayOf(0, 1), intArrayOf(1, 0)
    )
    var edgesInTheAir = 0

    for ((difY, difX) in directions) {
        val newY = y + difY
        val newX = x + difX

        if (inGrid(newY, newX, n, m) && inTheAir[newY][newX]) edgesInTheAir++
    }

    return edgesInTheAir >= 2
}