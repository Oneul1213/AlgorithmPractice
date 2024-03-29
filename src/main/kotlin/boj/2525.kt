/**
 * 백준 2525번 - 오븐시계
 *
 * 입력
 * 현재시 현재분
 * 조리분
 *
 * 출력
 * 조리완료시 조리완료분
 */
package boj

fun main() {
    val reader = System.`in`.bufferedReader()
    var (hours, minutes) = reader.readLine().split(" ").map { it.toInt() }
    val adder = reader.readLine().toInt()

    val adderHours = adder / 60
    val remainderMinutes = adder % 60

    hours += adderHours
    minutes += remainderMinutes

    if (minutes >= 60) {
        minutes %= 60
        hours += 1
    }

    if (hours >= 24) hours %= 24

    println("$hours $minutes")
}