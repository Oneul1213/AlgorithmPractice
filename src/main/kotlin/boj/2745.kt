/**
 * 백준 2745번 - 진법 변환
 * 알고리즘 분류 - 수학, 구현, 문자열
 *
 * 입력
 * B진법 수 N이 주어질 때,
 * 1. N B
 * (2 <= B <= 36),B진법 수 N을 10진법으로 바꾸면, 항상 10억보다 작거나 같음
 *
 * 출력
 * B진법 수 N을 10진법으로 출력
 */
fun main() = with(System.`in`.bufferedReader()) {
    val line = readLine().split(" ")
    val N = line[0]
    val B = line[1].toInt()

    val numMap = mutableMapOf<String, Int>()
    for (n in 0..9) {
        numMap["$n"] = n
    }
    var alphabet = 'A'
    for (n in 10..35) {
        numMap[(alphabet++).toString()] = n
    }

    var base = 1
    var decimal = 0
    for (c in N.reversed()) {
        numMap[c.toString()]?.let {
            decimal += (it * base)
            base *= B
        }
    }

    print(decimal)
}