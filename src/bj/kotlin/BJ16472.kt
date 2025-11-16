package bj.kotlin

import java.io.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()     // 사용할 수 있는 최대 서로 다른 문자 종류 수
    val string = br.readLine()        // 입력 문자열

    val alphabet = IntArray(26)       // 각 알파벳 등장 횟수 저장
    var count = 0                     // 현재 구간 안의 서로 다른 문자 개수
    var answer = 0                    // 만들 수 있는 최대 부분 문자열 길이
    var start = 0                     // 슬라이딩 윈도우 시작 인덱스

    // end: 슬라이딩 윈도우 끝 인덱스
    for (end in string.indices) {

        // 새로운 문자면 count 증가
        if (alphabet[string[end] - 'a']++ == 0) {
            count++
        }

        // 서로 다른 문자가 N을 초과하면 start를 당겨서 조절
        while (count > N && start < end) {
            if (--alphabet[string[start++] - 'a'] == 0)
                count--
        }

        // 최대 길이 갱신
        answer = maxOf(answer, end - start + 1)
    }

    println(answer)
}
