package breakout

import com.anysolo.toyGraphics.*
import com.anysolo.toyGraphics.events.EventManager
import com.anysolo.toyGraphics.events.KeyboardEvent
import com.anysolo.toyGraphics.events.MouseEvent
import org.jfugue.realtime.RealtimePlayer
import org.jfugue.theory.Note
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan

var gameIsOver = false


data class Area(val x: Int, val y: Int, val width: Int, val height: Int) {
    fun isInsideThis(a2: Area): Boolean =
        a2.y >= y && a2.y < y + height &&
        a2.x > x && a2.x < x + width
}


class Game {
    companion object {
        const val blockGapX = 4
        const val blockGapY = 4

        fun adjustWindowWidth(width: Int): Int {
            val w = Block.width + blockGapX

            return if ((width - blockGapX) % w == 0)
                width
            else
                ((width / w) + 1) * w + blockGapX
        }
    }

    private val wnd = Window(adjustWindowWidth(800), 500, buffered = true, background = Pal16.black)
    private val eventManager = EventManager(wnd)
    private val player = RealtimePlayer()

    private val platform = Platform(wnd.width/2, wnd.height - Platform.height - 6)
    private val ball = Ball(1.0,-1.0)
    private val blocks: Array<Block?> = createBlocks()
    var angle = PI/4
    private var score: Int = 0
    private var origonalSpeed = -2

    private fun showGameOver() {
        Graphics(wnd).use { g ->
            g.clear()
            g.color = Pal16.red
            g.setFontSize(35)
            g.drawText(wnd.width / 2, wnd.height / 2, "Game over")
            gameIsOver=true
            sleep(1)
        }
    }

    private fun amountOfBlockRows() = wnd.width / (Block.width + blockGapX)
    private fun amountOfBlockColumns() = wnd.height / (Block.height + blockGapY)

    private fun createBlocks(): Array<Block?> {
        val blocks = Array<Block?>(amountOfBlockRows() * amountOfBlockColumns()) {null}

        for(row in 0 until amountOfBlockRows()) {
            for (column in 0 until amountOfBlockColumns()) {
                blocks[row * amountOfBlockColumns() + column] = Block(
                    row * (Block.height + blockGapY) + blockGapY,
                    column * (Block.width + blockGapX) + blockGapX
                )
            }
        }

        return blocks
    }

    private fun drawFrame() {
        Graphics(wnd).use { g ->
            g.clear()

            platform.draw(g)
            ball.draw(g)
            g.drawText(wnd.width-6,wnd.height/2,"Score: $score")
            for(b in blocks)
                b?.draw(g)

            sleep(5)
        }
    }

    private fun updateObjects() {
        platform.update(wnd.width)
    }

    private fun onKeyboardEvent(event: KeyboardEvent) {
        when (event.code) {
            'Q'.toInt() -> gameIsOver = true
            else -> platform.onKeyEvent(event)
        }
    }



    private fun onMouseEvent(event: MouseEvent) { platform.x = event.pos.x - 50 }

    private fun processEvents() {
        while (true) {
            val event = eventManager.takeEvent() ?: break

            when(event) {
                is KeyboardEvent -> onKeyboardEvent(event)
                is MouseEvent -> onMouseEvent(event)
            }
        }
    }
    fun win(){
//        if (score>=14)
//          gameIsOver=true
   }

    fun processCollions() {
        val note2 = Note("C7T")
        if (platform.getArea().isInsideThis(ball.getArea())) {
                ball.speedX *=1
                ball.speedY *= -1
        }
        if(ball.x>=platform.x-10 &&
           ball.x<= platform.x+5 &&
            ball.y>=platform.y-5 &&
            ball.y<= platform.y+5
                )
        {
            //todo 1: Convert speedX and speedY to angle and speed
            //todo 2: change angle
            //todo 3: Convert angle and speed back to speedX and speedY

            var angle = Math.atan2(platform.y - 5.0 - ball.y, platform.x + 95.0 - ball.x)
            //todo: Opposite \/

//            ball.speedX = cos(angle) * origonalSpeed
//            ball.speedY = sin(angle) * origonalSpeed
            println("SpeedX: ${ball.speedX}, SpeedY: ${ball.speedY}")
        }else if(
            ball.x <=platform.x+95 &&
            ball.x >= platform.x+89 &&
            ball.y >= platform.y - 16 &&
            ball.y <= platform.y + 16
        ){
            var angle = Math.atan2(ball.y - platform.y - 5.0, ball.x - platform.x + 95.0)

//            ball.speedX = origonalSpeed * cos(angle)
//            ball.speedY = origonalSpeed * sin(angle)
            println("SpeedX: ${ball.speedX}, SpeedY: ${ball.speedY}")

        }

        for(i in blocks.indices) {
            val b = blocks[i]
            if (b != null) {
                    if (b.x + Block.width >= ball.x &&
                            b.x <= ball.x &&
                            b.y + Block.height >= ball.y &&
                            b.y <= ball.y
                    )
                    {
                        score += 1
                        ball.speedY = -ball.speedY
                        blocks[i] = null
                        playSound(note2)
                    }
            }
        }
    }
    fun bulletBounce(wnd: Window){
        val newx = ball.x + ball.speedX
        val newy = ball.y + ball.speedY


        if (newx + Ball.radius >= wnd.width || newx + Ball.radius <= 0) {
            playSound(Note("E7C"))
            ball.speedX = -ball.speedX
        }
        if (newy + Ball.radius <= 0) {
            ball.speedY = -ball.speedY
            playSound(Note("E7T"))
        }
        if(newy + Ball.radius >= wnd.height) {
            gameIsOver=true
        }


        ball.x += ball.speedX
        ball.y += ball.speedY

    }

    private fun playSound(pitch: Note) {
        player.stopNote(pitch)
        player.play(pitch)
    }

    fun run() {
        createBlocks()

        while(!gameIsOver) {
            processEvents()
            updateObjects()
            processCollions()


            bulletBounce(wnd)

            drawFrame()
            win()

        }
        while (gameIsOver){
            showGameOver()
        }

    }
}

fun main() {
    val game = Game()
    game.run()
}
