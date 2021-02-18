package tasks

fun main() {
    val a = intArrayOf(6, 5, 4, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 2, 1, 1, 1, 2, 4, 4, 6, 6, 6, 6, 6, 6, 6, 1, 5, 2, 4, 3, 7)
    var lenght = 1
    var longest = 0
    var elementId = 0

    for (i in 1..a.size - 1) {
        if (a[i - 1] == a[i]) {
//            println("is repeating ${i - 1},number ${a[i - 1]}")
            lenght += 1
        } else {
            if (lenght > 1) {
//                println(lenght)
                if (lenght > longest) {
                    longest = lenght
                    elementId = i - lenght
                }
//                println("is repeating ${i - 2},number ${a[i - 1]}")
            }
//            println("not repeating $i")
            lenght = 1
        }
    }
    println("the longest is $longest long and its spot is $elementId")
}