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
 *
 * 주의사항
 * - 각 dfs 마다 visited 를 만들어서 사용하면 1000*1000 ~= 1MB 크기의 visited 가
 *   1000 번 (M의 최대 값) 만들어져서 1000 MB가 됨(가비지 컬렉터가 메모리르 회수하기 때문)
 */
// TODO : 9% 틀렸습니다 해결
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

    // 모든 케이스에 대한 dfs
//    for (pair in searchList) {
//        println(dfs(pair[0], pair[1], adjArr))
//    }

    // 복구용 원본 생성
    val originArr = adjArr.copyOf()

    // 모든 케이스에 대한 dfs
    for (i in 0 until m) {
        val pair = reader.readLine().split(" ").map { it.toInt() }
        println(dfs(pair[0], pair[1], adjArr))

        // 원본 복구(메모리 대신 시간 사용)
        rollbackArray(originArr, adjArr)
    }
}

// visited 를 이용한 stack 기반 dfs
fun dfs(start: Int, end: Int, adjArr: Array<IntArray>): Int {
    val stack = ArrayDeque<Pair<Int, Int>>()
    val n = adjArr.size - 1

    // stack 에 시작값 넣기
    for ((index, element) in adjArr[start].withIndex()) {
        if (element != -1) {
            if (index == end) return adjArr[start][index]

            stack.add(Pair(index, adjArr[start][index]))
            adjArr[start][index] = -1
            adjArr[index][start] = -1
        }
    }

    // visited 보고 stack 에 담으면서 탐색 및 거리 계산
    while (stack.isNotEmpty()) {
        val (node, dist) = stack.removeLast()

        // node 에 연결된 다른 노드 탐색
        for ((index, element) in adjArr[node].withIndex()) {
            if (element != -1) {
                if (index == end) return dist + adjArr[node][index]

                stack.add(Pair(index, dist + adjArr[node][index]))
                adjArr[node][index] = -1
                adjArr[index][node] = -1
            }
        }
    }

    return -1
}

// 배열을 dfs 전으로 원상 복구
fun rollbackArray(origin: Array<IntArray>, changed: Array<IntArray>) {
    val len = origin.size

    for (i in 0 until len) {
        for (j in 0 until len) {
            if (origin[i][j] != changed[i][j]) {
                changed[i][j] = origin[i][j]
            }
        }
    }
}