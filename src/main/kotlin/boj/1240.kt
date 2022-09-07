/**
 * 백준 1240번 - 노드사이의 거리
 *
 * 입력
 * 1. 노드의 개수 N (2 <= N <= 1_000), 거리를 알고 싶은 노드 쌍의 수 M (M <= 1_000)
 * 2. N-1개의 트리 상에 연결된 두 점과 거리 (<= 10_000)
 * 3. M개의 노드쌍
 *
 * 출력
 * 1. M개의 노드쌍별 거리
 */
// TODO : 메모리 초과 해결 (1차원 배열로 해결하는 방법 고민.. index 를 행, value 를 열로?)
package boj

import java.util.StringTokenizer

fun main() {
    val reader = System.`in`.bufferedReader()
    var tokenizer = StringTokenizer(reader.readLine(), " ")
//    val (n, m) = reader.readLine().split(" ").map { it.toInt() }

    val n = tokenizer.nextToken().toInt()
    val m = tokenizer.nextToken().toInt()

    // 인접 행렬 (1부터 사용)
    val adjArr = Array(n+1) { IntArray(n+1) { -1 } }

    // n-1개 줄의 입력을 인접 행렬에 추가
    for (i in 1 until n) {
//        val (node1, node2, dist) = reader.readLine().split(" ").map { it.toInt() }
        tokenizer = StringTokenizer(reader.readLine(), " ")
        val node1 = tokenizer.nextToken().toInt()
        val node2 = tokenizer.nextToken().toInt()
        val dist = tokenizer.nextToken().toInt()

        adjArr[node1][node2] = dist
        adjArr[node2][node1] = dist
    }

//    val searchList = mutableListOf<List<Int>>()
//
//    // m 개의 거리 쌍 입력 받기
//    for (i in 0 until m) {
//        val pair = reader.readLine().split(" ").map { it.toInt() }
//        searchList.add(pair)
//    }

    // 모든 케이스에 대한 dfs
//    for (pair in searchList) {
//        println(dfs(pair[0], pair[1], adjArr))
//    }
    for (i in 0 until m) {
        tokenizer = StringTokenizer(reader.readLine(), " ")
        val start = tokenizer.nextToken().toInt()
        val end = tokenizer.nextToken().toInt()
        println(dfs(start, end, adjArr))
    }
}

// visited 를 이용한 stack 기반 dfs
fun dfs(start: Int, end: Int, adjArr: Array<IntArray>): Int {
    val stack = ArrayDeque<Pair<Int, Int>>()
    val n = adjArr.size - 1

    // 탐색용 visited 행렬 생성 및 초기화
    val visited = Array(n + 1) { BooleanArray(n + 1) { true } }

    for (i in 1..n) {
        for (j in 1 .. n) {
            if (adjArr[i][j] != -1) visited[i][j] = false
        }
    }

    // stack 에 시작값 넣기
    for ((index, isVisited) in visited[start].withIndex()) {
        if (!isVisited) {
            if (index == end) return adjArr[start][index]

            stack.add(Pair(index, adjArr[start][index]))
            visited[start][index] = true
            visited[index][start] = true
        }
    }

    // visited 보고 stack 에 담으면서 탐색 및 거리 계산
    while (stack.isNotEmpty()) {
        val (node, dist) = stack.removeLast()

        // node 에 연결된 다른 노드 탐색
        for ((index, isVisited) in visited[node].withIndex()) {
            if (!isVisited) {
                if (index == end) return dist + adjArr[node][index]

                stack.add(Pair(index, dist + adjArr[node][index]))
                visited[node][index] = true
                visited[index][node] = true
            }
        }
    }

    return -1
}