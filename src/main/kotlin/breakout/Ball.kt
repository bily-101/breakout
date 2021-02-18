package breakout

import com.anysolo.toyGraphics.Graphics
import com.anysolo.toyGraphics.Pal16
import kotlin.random.Random


class Ball(var speedX: Double, var speedY: Double) {
    var x: Double = 90.0
    var y: Double = 420.0

    companion object {
        const val size = 10
        const val radius = size/2
    }

    fun getArea() = Area(x.toInt(), y.toInt(), Platform.width, Platform.height)

    fun draw(gc: Graphics) {
        gc.color = Pal16.red
        gc.drawOval(x.toInt(), y.toInt(), size, size, fill = true)
    }

    fun mulSpeed(kh: Int, kv: Int) {
        speedY *= kv
    }


}
