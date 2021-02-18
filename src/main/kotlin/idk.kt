package level2.unit1.tasks.beatifl

import com.anysolo.toyGraphics.*
import kotlin.math.roundToInt
import kotlin.random.Random


private data class Flyer(var x: Double, var y: Double, val color: Color, var speedX: Double, var speedY: Double, var width: Int, var height: Int) {
    fun move() {
        x += speedX
        y += speedY
    }

    fun draw(g: Graphics) {
        g.color = color
        g.drawRect(x.roundToInt(), y.roundToInt(), width, height, fill = true)
    }
}


fun main() {
    val wnd = Window(800, 875, buffered = true, background = Pal16.black)
    val flyers = mutableListOf<Flyer>()


    while (true) {
        val newFlyer = Flyer(
                x = wnd.width / 2.0,
                y = wnd.height / 2.0,
                color = Pal16[Random.nextInt(1, 16)],
                speedX = Random.nextDouble(-1.0, 1.0),
                speedY = Random.nextDouble(-1.0, 1.0),
                width = Random.nextInt(1, 10),
                height = Random.nextInt(1, 10)
        )

        flyers.add(newFlyer)

        val g = Graphics(wnd)
        g.color = Pal16.white
        g.clear()

        for (f in flyers) {
            f.move()
            f.draw(g)
        }

        sleep(0)

        g.close()
    }
}