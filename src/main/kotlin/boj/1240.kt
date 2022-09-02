/**
 * 백준 1240번 - 노드사이의 거리
 *
 * 입력
 * 1. 노드의 개수 N, 거리를 알고 싶은 노드 쌍의 수 M
 * 2. N-1개의 트리 상에 연결된 두 점과 거리
 * 3. M개의 노드쌍
 *
 * 출력
 * 1. M개의 노드쌍별 거리
 */
package boj

fun main() {
    val reader = System.`in`.bufferedReader()
    val (n, m) = reader.readLine().split(" ").map { it.toInt() }

    // 인접 행렬 (1부터 사용)
    val adjArr = Array(n+1) { IntArray(n+1) { -1 } }

    // n-1개 줄의 입력을 인접 행렬에 추가
    for (i in 1 until n) {
        val (node1, node2, dist) = reader.readLine().split(" ").map { it.toInt() }
        adjArr[node1][node2] = dist
        adjArr[node2][node1] = dist
    }

    val searchList = mutableListOf<List<Int>>()

    // m 개의 거리 쌍 입력 받기
    for (i in 0 until m) {
        val pair = reader.readLine().split(" ").map { it.toInt() }
        searchList.add(pair)
    }

    // visited 를 이용한 BFS
    for (pair in searchList) {
        println(bfs(pair[0], pair[1], adjArr))
    }
}

fun bfs(start: Int, end: Int, adjArr: Array<IntArray>): Int {
    var distance = 0
    val q = ArrayDeque<Pair<Int, Int>>()
    val n = adjArr.size

    // 탐색용 visited 행렬 생성 및 초기화
    val visited = Array(n + 1) { BooleanArray(n + 1) { true } }

    for (i in 1..n) {
        for (j in 1 .. n) {
            if (adjArr[i][j] != -1) visited[i][j] = false
        }
    }

    // TODO : visited 보고 q에 담으면서 탐색 및 거리 계산

    return distance
}