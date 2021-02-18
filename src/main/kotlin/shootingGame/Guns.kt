package shootingGame

import com.anysolo.toyGraphics.Graphics
import com.anysolo.toyGraphics.Pal16
import com.anysolo.toyGraphics.Window
import kotlin.math.*

class Guns(var x: Double, var y : Double) {

    companion object{
        var bulletSpeedX = 1.0
        var bulletSpeedY = 1.0
        var bulletY = 1.0
        var bulletX = 1.0
    }

    fun turnTheGun(degreeDelta: Double) {
        Gun.pistol.gunAngle += degreeDelta

        val minimumAngle = PI / 8

        if(Gun.pistol.gunAngle < minimumAngle)
            Gun.pistol.gunAngle = minimumAngle

        x = 30.0 * cos(Gun.pistol.gunAngle)
        y = 30.0 * sin(Gun.pistol.gunAngle)
    }
    fun drawBullet(g: Graphics, wnd: Window){
        bulletX *= bulletSpeedX
        bulletY *= bulletSpeedY

        g.color = Pal16.black
        g.drawOval(bulletX.roundToInt(), (wnd.width -1 - bulletY).roundToInt(),
            5,5)

    }
//    fun calculateProjectile() {
//        projectileDistance += projectileHorizontalSpeed * .1
//        projectileAltitude += projectileVerticalSpeed * timeStep
//    }

    fun drawPistol(g: Graphics, wnd : Window){


        g.color = Pal16.black
        g.drawLine(0, (wnd.height-5), x.roundToInt(), (wnd.width -5 - y).roundToInt())

    }
    fun fire(pistol: Gun){
        bulletSpeedX = 1.5 * sin(pistol.pistol.gunAngle)
        bulletSpeedY = 1.5 * cos(pistol.pistol.gunAngle)
    }
}