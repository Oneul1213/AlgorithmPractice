/**
 * 백준 1013번 - Contact
 *
 * 입력
 * 1. 테스트케이스 개수 T
 * 2. 전파를 표현하는  { 0, 1 } 만으로 이루어진 문자열 (1 <= 길이 N <= 200)
 *
 * 출력
 * 1. 제시한 패턴 ((100+1+|01)+) 이면 YES, 그렇지 않으면 NO
 */
package boj

fun main() {
    val reader = System.`in`.bufferedReader()
    val t = reader.readLine().trim().toInt()

    val regex = Regex("(100+1+|01)+")

    for (i in 0 until t) {
        val str = reader.readLine().trim()

        if (str.matches(regex)) println("YES")
        else println("NO")
    }
}