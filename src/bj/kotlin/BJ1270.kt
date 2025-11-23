package bj.kotlin

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()

    val map = HashMap<Long, Int>()

    repeat(n) {
        val st = StringTokenizer(br.readLine())

        val t = st.nextToken().toInt()
        var max = 0                 // 가장 많이 등장한 횟수
        var idx = 0L                // 가장 많이 등장한 값

        repeat(t) {
            val num = st.nextToken().toLong()
            val count = map.getOrDefault(num, 0) + 1
            map[num] = count

            // 현재 숫자가 최대 빈도수 갱신하면 업데이트
            if (count > max) {
                max = count
                idx = num
            }
        }

        // 절반 초과로 등장하면 이긴 진영, 아니면 SYJKGW
        if (max > t / 2) {
            println(idx)
        } else {
            println("SYJKGW")
        }

        map.clear() // 다음 테스트 케이스를 위해 초기화
    }
}
