package experiment

import com.anysolo.toyGraphics.Graphics
import com.anysolo.toyGraphics.Window
import com.anysolo.toyGraphics.sleep

class simulator(var x: Int, var y: Int){
    var wnd = Window(500,500)


    fun drawPerson(g: Graphics){
        //Arm Right
        g.drawLine(x,y,x,y+30)
        //Arm Left
        g.drawLine(x+100,y+100,x,y+30)

    }

    fun run() {
        while (true) {
            var g = Graphics(wnd)
            g.clear()
            drawPerson(g)
            sleep(10)
        }
    }

}

fun main () {
    var sim = simulator(20,40)
    sim.run()
}