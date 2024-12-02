/**
 * 백준 10798번 - 세로 읽기
 * 알고리즘 분류 - 구현, 문자열
 *
 * 입력
 * 다섯 줄의 입력.
 * 각 줄에는 최소 1개, 최대 15개의 글자들이 빈칸 없이 연속으로 주어짐
 * 각 문자는 'A'-'Z', 'a'-'z', '0'-'9' 중 하나
 * 각 줄의 시작과 마지막 빈칸 없음
 *
 * 출력
 * 세로로 읽은 글자를 공백 없이 연속해서 출력
 */
fun main() = with(System.`in`.bufferedReader()) {
    var maxLength = 0
    val strList = mutableListOf<String>()
    for (i in 0 until 5) {
        val line = readLine()
        if (line.length > maxLength) {
            maxLength = line.length
        }
        strList.add(line)
    }

    var result = ""
    for (i in 0 until maxLength) {
        for (j in 0 until 5) {
            if (strList[j].length >= i+1) {
                result += strList[j][i]
            }
        }
    }

    println(result)
}