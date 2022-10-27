/**
 * 백준 18352번 - 특정 거리의 도시 찾기
 * https://www.acmicpc.net/problem/18352
 *
 * 제한
 * 시간 : 2초, 메모리 : 256MB
 *
 * 입력
 * 1. 도시의 개수 N 도로의 개수 M 거리 정보 K 출발 도시의 번호 X
 *    (2 <= N <= 300_000, 1 <= M <= 1_000_000, 1 <= K <= 300_000, 1 <= X <= N)
 * 2. M개의 줄에 A B (A->B 단방향 도로가 존재한다. 1 <= A, B <= N, A != B)
 *
 * 출력
 * 1. X로 부터 출발하여 도달할 수 있는 도시 중, 최단 거리가 K인 모든 도시의 번호를 한 줄에 하나씩 오름차순
 */
package boj

import java.util.PriorityQueue

fun main() {
    val reader = System.`in`.bufferedReader()
    val (n, m, k, x) = reader.readLine().split(" ").map { it.toInt() }
    val distances = IntArray(n + 1) { Int.MAX_VALUE }

    val adjList = Array(n + 1) { mutableListOf<Int>() }
    repeat(m) {
        val (from, to) = reader.readLine().split(" ").map { it.toInt() }
        adjList[from].add(to)
    }

    dijkstra(x, distances, adjList)

    if (!distances.contains(k)) {
        println(-1)
        return
    }

    for ((city, distance) in distances.withIndex()) {
        if (distance == k) println(city)
    }
}

fun dijkstra(start: Int, distances: IntArray, adjList: Array<MutableList<Int>>) {
    distances[start] = 0
    // pair 의 second(거리)로 두 Pair 를 비교
    val pq = PriorityQueue(Comparator<Pair<Int, Int>> { a, b -> a.second - b.second })
    pq.add(Pair(start, 0))

    while (pq.isNotEmpty()) {
        val (current, distance) = pq.remove()

        // 시작 노드에서 현재 노드까지의 거리(distance)보다 distances 에 갱신되어있는 최단 거리가 더 짧으면 무시
        // 즉, 이미 최단거리로 갱신된 상태라면 무시
        if (distances[current] < distance) continue
        for (adjCity in adjList[current]) {
            val nextDistance = distance + 1
            if (nextDistance < distances[adjCity]) {
                distances[adjCity] = nextDistance
                pq.add(Pair(adjCity, nextDistance))
            }
        }
    }
}