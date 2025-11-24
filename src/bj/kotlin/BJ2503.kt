package bj.kotlin

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()

    // 정답 후보 저장
    val check = BooleanArray(1000)

    // 123 ~ 999 중에서 가능한 숫자 후보만 추리기
    for (i in 123 until 1000) {
        val str = i.toString()

        // 각 자리수에 0이 있는 경우 제외
        if ('0' in str) continue

        // 중복 숫자 있는 경우 제외
        if (str[0] == str[1] || str[0] == str[2] || str[1] == str[2]) continue

        check[i] = true
    }

    repeat(N) {
        val st = StringTokenizer(br.readLine())
        val req = st.nextToken().toInt()
        val s = st.nextToken().toInt()
        val b = st.nextToken().toInt()

        // 입력 조건과 비교하여 후보를 제거
        for (ans in 123 until 1000) {
            if (check[ans]) {
                var strike = 0
                var ball = 0

                val reqStr = req.toString()
                val ansStr = ans.toString()

                // req의 각 자리 vs 후보 각 자리 비교
                for (i1 in 0 until 3) {
                    for (i2 in 0 until 3) {
                        if (reqStr[i1] == ansStr[i2]) {
                            if (i1 == i2) strike++
                            else ball++
                        }
                    }
                }

                // 스트라이크/볼 조건이 맞지 않으면 후보 제외
                if (strike != s || ball != b) {
                    check[ans] = false
                }
            }
        }
    }

    // 남아있는 후보 개수 출력
    var answer = 0
    for (i in 123 until 1000) {
        if (check[i]) answer++
    }

    println(answer)
}
