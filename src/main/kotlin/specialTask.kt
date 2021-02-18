fun shiftArrayRight(a: IntArray) {

    for(i in a.indices -1){
        if (a.size - i == a.size)
            break
        var swap =a[a.size - i - 1]
        a[a.size - i] = swap


    }
    a[0] = 0
}

fun main() {
    val a = intArrayOf(40, 2, 11, 7, 9, 5)
    val expectedArray = intArrayOf(0, 40, 2, 11, 7, 9)

    shiftArrayRight(a)
    if(a.toList() == expectedArray.toList())
        println("Passed")
    else
        println("Failed. a: ${a.toList()}" )
}