package breakout

import com.anysolo.toyGraphics.Color
import com.anysolo.toyGraphics.Graphics


class Block(var y: Int, var x: Int){
    companion object {
        const val height = 20
        const val width = 60
        const val borderWidth = 2

        private val baseColor = Color(66, 135, 245)
        const val shadowK = 0.2

        fun makeBodyColor(): Color {
            val hsb = baseColor.toHSB()
            return Color(hsb.copy(brightness = hsb.brightness * (1 - shadowK)))
        }

        fun makeShadow1Color(): Color = baseColor

        fun makeShadow2Color(): Color {
            val hsb = baseColor.toHSB()
            return Color(hsb.copy(brightness = hsb.brightness * (1 - 2*shadowK)))
        }
    }

    fun draw(g: Graphics) {
        g.color = makeBodyColor()
        g.drawRect(x, y, width, height, fill = true)

        g.setStrokeWidth(borderWidth)

        // ---------- Drawing shadows ----------

        g.color = makeShadow1Color()
        // top
        g.drawLine(x + borderWidth/2, y, x + width - 1 - borderWidth/2, y)

        // right
        val line2X = x + width - 1 - borderWidth/2
        g.drawLine(line2X, y + borderWidth/2, line2X, y + height - 1 - borderWidth/2)

        g.color = makeShadow2Color()
        // left
        val line4X = x + borderWidth/2
        g.drawLine(line4X, y + borderWidth/2, line4X, y + height - 1 - borderWidth/2)

        // bottom
        val line3Y = y + height - 1 - borderWidth/2
        g.drawLine(x + borderWidth/2, line3Y, x + width - 1 - borderWidth - 1, line3Y)
    }
}
