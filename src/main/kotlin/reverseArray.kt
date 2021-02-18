import kotlin.random.Random


fun reverse(a: IntArray) {
    for (i in 0 until a.size / 2) {
        val tmp = a[i]

        a[i] = a[a.size - 1 - i]
        a[a.size - 1 - i] = tmp
    }
}

fun reverseTest(arraySize: Int){
    //7,8,1
    //1,7,8
    //make it original aray
    //copy the array
    //sort the array in opposite order
    //8,7,1
    // call it the "second array"
    // reverse the first array and compare with the second
    val a = IntArray(arraySize)
    fillWithRandoms(a)
    a.sortDescending()

    val b: IntArray = a.copyOf()
    b.sort()

    reverse(a)

    if(b.contentEquals(a))
        println("true pogggerasz")
    else
        println("failer po")
}

fun fillWithRandoms(a: IntArray, from: Int = -1000, to: Int = 1000) {
    for (i in 0 until a.size)
        a[i] = Random.nextInt(from, to)
}

fun main(){
    reverseTest(0)
    reverseTest(6)
    reverseTest(8)
    reverseTest(1000)
    reverseTest(1110)
}