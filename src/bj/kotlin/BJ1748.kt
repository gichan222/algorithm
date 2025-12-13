package bj.kotlin

fun main(){
    var num = readLine()!!.toInt()

    var plus = 1
    var sum = 0
    var divider = 10
    for(i in 1..num){

        if(i%divider==0){
            divider *=10
            plus++
        }

        sum+=plus
    }
    println(sum)
}