/**
 * 백준 10988번 - 팰린드롬인지 확인하기
 * 알고리즘 분류 - 구현, 문자열
 *
 * 입력
 * 1. 영어 소문자로만 이루어진 단어. 길이는 1보다 크거나 같고 100보다 작거나 같음
 *
 * 출력
 * 1. 팰린드롬이면 1, 아니면 0
 */
fun main() = with(System.`in`.bufferedReader()) {
    val word: String = readLine()
    var isPalindrome = 1

    for (i in 0..(word.length / 2)) {
        if (word[i] !== word[word.length - 1 - i]) {
            isPalindrome = 0
            break
        }
    }

    println(isPalindrome)
}