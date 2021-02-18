package shootingGame

import com.anysolo.toyGraphics.*
import kotlin.math.PI

class Game() {
    val wnd = Window(500,500)
    var keyboard = Keyboard(wnd)


    var guns: Guns = Guns(250.0,250.0)
    fun keyboard(keyboard: Keyboard) {
        while (true) {
            val key = keyboard.getPressedKey() ?: break

            when (key.code) {
                KeyCodes.LEFT ->
                    guns.turnTheGun(+PI / 360)

                KeyCodes.RIGHT ->
                    guns.turnTheGun(-PI / 360)
                KeyCodes.SPACE ->
                    guns.fire(Gun)
            }
        }
    }
    private fun draw(g: Graphics){
        guns.drawBullet(g,wnd)
        guns.drawPistol(g, wnd)
    }



    fun run(){
        guns.turnTheGun(0.0)
        while(true){
            var g = Graphics(wnd)
            g.clear()
            keyboard(keyboard)
            draw(g)
            sleep(10)
        }
    }
}



fun main() {
    val game = Game()
    game.run()
}