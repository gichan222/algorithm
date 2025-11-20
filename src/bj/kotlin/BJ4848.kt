package bj.kotlin

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    var T = br.readLine().toInt()

    val map = HashMap<Int, String>() // 원본 숫자 -> 문자 맵
    val stringMap = HashMap<String, Int>() // 원본 문자 -> 숫자 맵
    map[0] = "{}"
    stringMap["{}"] = 0

    val sb = StringBuilder()
    for(i in 1..15){
        sb.setLength(0)
        sb.append("{")
        for(j in 0..i-2){
            sb.append(map[j]).append(",")
        }
        sb.append(map[i-1])
        sb.append("}")
        map[i] = sb.toString()
        stringMap[sb.toString()] = i
    }

    while(T-- > 0){
        val first = br.readLine()
        val second = br.readLine()
        val sum = stringMap[first]!! + stringMap[second]!!
        println(map[sum])
    }
}