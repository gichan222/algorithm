package bj.kotlin

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var inputNum = br.readLine().toLong() // 입력값 읽기

    var outputNum = 0L
    while (inputNum > 0L) {
        var tempCnt = 0L
        var num = 1L
        // inputNum보다 작은 최대 2의 거듭제곱 찾기
        while (inputNum >= num * 2L) {
            num *= 2
            tempCnt++
        }
        inputNum -= num  // 찾은 2의 거듭제곱만큼 빼기
        outputNum += pow(tempCnt)  // 3의 거듭제곱 계산하여 합산
    }

    println(outputNum)
}

fun pow(tempCnt: Long): Long {
    var retVal = 1L
    repeat(tempCnt.toInt()) {
        retVal *= 3
    }
    return retVal
}
