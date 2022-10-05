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
    val grid = Array(n) { IntArray(m) }
    val visited = Array(n) { BooleanArray(m) { false } }

    // grid, visited 초기화
    for (i in 0 until n) {
        val line = reader.readLine().split(" ").map { it.toInt() }.toIntArray()
        grid[i] = line

        for (j in 0 until m) {
            // TODO : 치즈 안 쪽 빈 공간인지를 어떻게 판단해서 걔를 false 로 만들건지?
            visited[i][j] = line[j] == 0
        }
    }

    // 치즈 바깥 부분만 visited 배열에 방문했다 하고 bfs 중 두 칸이 안 갔던 곳이면 grid 에서 그 칸 0으로 변경.
    // 이렇게 총 시간 을 세면 끝.
}

fun bfs() {

}