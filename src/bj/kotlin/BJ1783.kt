package bj.kotlin

fun main() {
    val br = System.`in`.bufferedReader()

    val (rows, cols) = br.readLine().split(' ').map { it.toInt() }

    val result =
        if (rows == 1) {
            "1"
        } else if (rows == 2) {
            "${minOf((cols + 1) / 2, 4)}"
        } else {
            if (cols < 7) "${minOf(cols, 4)}" else "${cols - 2}"
        }

    println(result)
}
