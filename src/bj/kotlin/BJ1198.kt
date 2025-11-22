package bj.kotlin

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs
import kotlin.math.max

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val n = br.readLine().toInt()
    val points = Array(n) {
        br.readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    var maxArea = 0.0

    // 삼각형 가능한 모든 조합 탐색
    for (a in 0 until n - 2) {
        for (b in a + 1 until n - 1) {
            for (c in b + 1 until n) {
                maxArea = max(maxArea, area(points[a], points[b], points[c]))
            }
        }
    }

    println(maxArea)
}

// 신발끈 공식으로 삼각형 넓이 구하기
fun area(a: IntArray, b: IntArray, c: IntArray): Double {
    val value = abs(
        a[0] * b[1] + b[0] * c[1] + c[0] * a[1]
                - a[1] * b[0] - b[1] * c[0] - c[1] * a[0]
    )
    return value / 2.0
}
