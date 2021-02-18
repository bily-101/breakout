import com.anysolo.toyGraphics.Keyboard
import com.anysolo.toyGraphics.Window
import com.anysolo.toyGraphics.sleep
import org.jfugue.player.Player

fun  main() {
    val wnd = Window(800, 600, buffered = true)
    val player = Player()
    val keyboard = Keyboard(wnd)
    sleep(10)

//    player.play("C3MAJq F3MAJq G3MAJq C3MAJq A3MAJq ")
    while(true) {1
        val key = keyboard.getPressedKey()

        // Use {} block to conditionally execute many lines of code
        if(key != null) {
            when (key.code) {
                '1'.toInt() -> player.play("C3MAJq")
                '2'.toInt() -> player.play("D3MAJq")
                '3'.toInt() -> player.play("E3MAJq")
                '4'.toInt() -> player.play("F3MAJq")
                '5'.toInt() -> player.play("G3MAJq")
                '6'.toInt() -> player.play("A3MAJq")
                '7'.toInt() -> player.play("B3MAJq")

            }
        }
        sleep(1)
    }

    println("The End")
    wnd.close()
}

