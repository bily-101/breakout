package breakout

import com.anysolo.toyGraphics.*
import com.anysolo.toyGraphics.events.KeyboardEvent


class Platform(var x:Int, var y:Int) {
    var speedX = 0


    companion object {
        val width = 100
        val height = 10
        const val speedQuant = 2
    }

    fun getArea() = Area(x, y, width, height)

    fun draw(g: Graphics) {
        g.color = Pal16.green
        g.drawRect(x, y, width, height)

        g.color = Pal16.red
        g.drawOval(x-10,y-5,16,16, fill = true)
        g.drawOval(x+ width-5,y-5,16,16, fill = true)
    }

    fun update(wndWidth: Int) {
        x += speedX

        if (x >= wndWidth){
            x = 2
        }

        if(x<=0){
            x = wndWidth - 5
        }
    }

    fun onKeyEvent(keyEvent: KeyboardEvent) {
        if(keyEvent.isPressed) {
            when (keyEvent.code) {
                'A'.toInt(), KeyCodes.LEFT -> speedX -= speedQuant
                'D'.toInt(), KeyCodes.RIGHT -> speedX += speedQuant
                'S'.toInt() -> speedX = 0
            }
        }
    }
}
