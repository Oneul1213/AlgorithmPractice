/**
 * 백준 1012번 - 유기농 배추
 * 알고리즘 분류 - 그래프 이론
 *
 * 입력
 * 1. 테스트 케이스의 개수 T
 * 2. 배추밭의 가로길이 M (1 <= M <= 50), 세로길이 N (1 <= N <= 50), 배추의 개수 K (1 <= K <= 2500)
 * 3. K 줄 동안 배추의 위치 X (1 <= X <= M-1), Y (1 <= Y <= N-1)
 * 4. 2~3 T 만큼 반복
 *
 * 출력
 * 1. 테스트케이스에서 필요한 최소 배추 흰 지렁이의 수
 * 2. 1을 T 만큼 반복
 */
package boj

fun main() {
    val reader = System.`in`.bufferedReader()
    val t = reader.readLine().toInt()
    val earthWorms = IntArray(t) { 0 }

    // 모든 case 에 대해
    for (test in 0 until t) {
        val (m, n, k) = reader.readLine().split(" ").map { it.toInt() }

        // field, visited 초기화
        val field = Array(n) { IntArray(m) }
        val visited = Array(n) { BooleanArray(m) { true } }

        for (cabbage in 0 until k) {
            val (x, y) = reader.readLine().split(" ").map { it.toInt() }
            field[y][x] = 1
            visited[y][x] = false
        }

        // 모든 위치에 대해 bfs 수행
        for (y in 0 until n) {
            for (x in 0 until m) {
                if (bfs(y, x, n, m, visited)) earthWorms[test]++
            }
        }
    }

    for (worm in earthWorms) println(worm)
}

fun canGo(y: Int, x: Int, n: Int, m: Int, visited: Array<BooleanArray>): Boolean =
    y in 0 until n && x in 0 until m && !visited[y][x]

fun bfs(y: Int, x: Int, n: Int, m: Int, visited: Array<BooleanArray>): Boolean {
    // 초기위치 y, x에서 시작하는 BFS
    val dydx = listOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1))

    if (visited[y][x]) return false

    val q = ArrayDeque<Pair<Int, Int>>()
    q.add(Pair(y, x))
    visited[y][x] = true

    while (q.isNotEmpty()) {
        val (curY, curX) = q.removeFirst()

        for ((dy, dx) in dydx) {
            val newY = curY + dy
            val newX = curX + dx

            if (canGo(newY, newX, n, m, visited)) {
                q.add(Pair(newY, newX))
                visited[newY][newX] = true
            }
        }
    }

    return true
}