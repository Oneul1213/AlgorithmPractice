/**
 * 백준 1213번 - 팰린드롬 만들기
 *
 * 입력
 * 1. 팰린드롬으로 변경할 영어 이름(알파벳 최대 50글자)
 *
 * 출력
 * 1. 사전순으로 앞서는 팰린드롬으로 변경한 영어 이름 또는 I'm Sorry Hansoo(불가능할 경우)
 */
package boj

fun main() {
    val reader = System.`in`.bufferedReader()
    val inputDeque = ArrayDeque<String>()
    inputDeque.addAll(reader.readLine().split("").sorted())

    val remainQ = ArrayDeque<String>()
    val palindromeBuilder = StringBuilder()

    while (inputDeque.size > 1) {
        val current = inputDeque.removeFirst()
        val next = inputDeque.removeFirst()

        if (current == next) palindromeBuilder.append(current)
        else {
            remainQ.add(current)
            inputDeque.addFirst(next)
        }
    }

    // 남은 하나를 remainQ에 넣어줌.
    if (inputDeque.isNotEmpty()) remainQ.add(inputDeque.removeFirst())

    if (remainQ.size > 1) println("I'm Sorry Hansoo")
    else {
        val front = palindromeBuilder.toString()
        if (remainQ.isNotEmpty()) palindromeBuilder.append(remainQ.removeFirst())
        palindromeBuilder.append(front.reversed())
        println(palindromeBuilder.toString())
    }
}