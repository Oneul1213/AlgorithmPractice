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
 *   1000 번 (M의 최대 값) 만들어져서 1000 MB가 됨(가비지 컬렉터가 메모리를 회수하기 때문)
 *
 * 문제가 생겼던 부분
 * 1. 런타임 에러(NoSuchElement) (min(distance)를 사용할 때 distance 가 비어있을 때)
 * 2. 2차원 배열을 복사할 때 단순히 origin = adjArr.copyOf() 하게 되면 바깥 배열만 깊은 복사된다.
 *    온전한 깊은 복사를 하려면 내부 배열에 일일히 adjArr[i].copyOf()를 해주어야 함.
 *
 * 반례
7 2
1 2 1
2 3 2
2 4 1
4 6 1
2 5 4
5 7 2
1 6
5 7
 * 정답 : 3
 *       2
 */
package boj

import java.util.Collections.min

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

    // 복구용 원본 생성
    val originArr = Array(n+1) { IntArray(n+1) { -1 } }
    for (i in originArr.indices) {
        originArr[i] = adjArr[i].copyOf()
    }

    // 가능한 모든 거리를 보관하기 위한 List
    val distance = mutableListOf<Int>()

    // 모든 케이스에 대한 dfs
    for (i in 0 until m) {
        val pair = reader.readLine().split(" ").map { it.toInt() }
        dfs(pair[0], pair[1], adjArr, distance)
        // 갈 수 없는 곳이면 0 출력
        if (distance.isEmpty()) println(0)
        // 그 외에는 최단거리 출력
        else println(min(distance))
        distance.clear()

        // 원본 복구(메모리 대신 시간 사용)
        rollbackArray(originArr, adjArr)
    }
}

// stack 기반 dfs
fun dfs(start: Int, end: Int, adjArr: Array<IntArray>, distance: MutableList<Int>): Int {
    val stack = ArrayDeque<Pair<Int, Int>>()

    // stack 에 시작값 넣기
    for ((index, element) in adjArr[start].withIndex()) {
        if (element != -1) {
            // 도착하면 거리 배열에 담고 break
            if (index == end) {
                distance.add(adjArr[start][index])
                break
            }

            stack.add(Pair(index, adjArr[start][index]))
            adjArr[start][index] = -1
            adjArr[index][start] = -1
        }
    }

    // stack 에 담으면서 탐색 및 거리 계산
    while (stack.isNotEmpty()) {
        val (node, dist) = stack.removeLast()

        // node 에 연결된 다른 노드 탐색
        for ((index, element) in adjArr[node].withIndex()) {
            if (element != -1) {
                if (index == end) {
                    distance.add(dist + adjArr[node][index])
                    break
                }

                stack.add(Pair(index, dist + adjArr[node][index]))
                adjArr[node][index] = -1
                adjArr[index][node] = -1
            }
        }
    }

    return 0
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