package tasks


data class MyFrac(val numerater: Int, val denominater: Int){
    operator fun times(f2: MyFrac) = MyFrac(
            numerater * f2.numerater,
            denominater * f2.denominater
    )

    operator fun div(f2: MyFrac) =
            this * MyFrac(f2.denominater, f2.numerater)

    override fun toString() = "$numerater/$denominater"

    operator fun times(n: Int) =
            MyFrac(numerater * n, denominater)


}

fun main(){
    val f1 = MyFrac(1, 2)
    val f2 = MyFrac(3, 2)

    println((f1 * f2) * 10)
}