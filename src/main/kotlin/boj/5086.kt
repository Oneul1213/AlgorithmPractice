/**
 * 백준 5086번 - 배수와 약수
 * 알고리즘 분류 - 수학, 사칙연산
 *
 * 입력
 * 한 줄에 10,000이 넘지 않는 두 자연수로 이루어짐.
 * 마지막 줄에 0이 2개 주어짐
 * 두 수가 같은 경우는 없음
 *
 * 출력
 * 각 줄에
 * 첫 번째 숫자가 두 번째 숫자의 약수라면 factor, 배수라면 multiple, 둘 다 아니라면 neither
 */
fun main() = with(System.`in`.bufferedReader()) {
    while (true) {
        val (a, b) = readLine().split(" ")

        val A: Int = a.toInt()
        val B: Int = b.toInt()

        if (A == 0 && B == 0) break

        if (B % A == 0) println("factor")
        else if (A % B == 0) println("multiple")
        else println("neither")
    }
}