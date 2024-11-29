/**
 * 백준 25206번 - 너의 평점은
 * 알고리즘 분류 - 수학, 구현, 문자열
 *
 * 입력
 * 1. 전공 과목명 학점 등급 (20줄)
 * 1 <= 과목명 길이 <= 50, 과목명은 알파벳 대소문자 또는 숫자, 띄어쓰기 없음, 입력으로 주어지는 모든 과목명은 다름
 * 학점 : 1.0, 2.0, 3.0, 4.0 중 하나
 * 등급 : A+, A0, B+, B0, C+, C0, D+, D0, F, P 중 하나
 * 적어도 한 과목은 등급이 P가 아님이 보장
 *
 * 출력
 * 1. 전공평점 (정답과 절대오차 또는 상대오차가 10^(-4) 이하면 정답으로 인정
 */
fun main() = with(System.`in`.bufferedReader()) {
    val gradeMap = mapOf(
        "A+" to 4.5,
        "A0" to 4.0,
        "B+" to 3.5,
        "B0" to 3.0,
        "C+" to 2.5,
        "C0" to 2.0,
        "D+" to 1.5,
        "D0" to 1.0,
        "F" to 0.0
    )
    var total = 0.0
    var totalScore = 0.0
    for (i in 0 until 20) {
        val input = readLine().split(" ")
        val score = input[1].toString().toDouble()
        val grade = input[2].toString()

        if (grade == "P") {
            continue
        }

        gradeMap[grade]?.let {
            totalScore += score
            total += (it * score)
        }
    }

    println(total / totalScore)
}
