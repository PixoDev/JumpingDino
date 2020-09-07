package components
import com.soywiz.klock.TimeSpan
import com.soywiz.korev.Key
import com.soywiz.korge.input.keys
import com.soywiz.korge.view.*
import com.soywiz.korge.view.addUpdater
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import constants.GameStatus
import constants.PlayerStatus
import utils.Coordinates


class Player(private val game: GameManager): Container() {

    private var status = PlayerStatus.RUNNING
    private var speedFactor = 10.0
    private val yLimit = 150.0
    private val initialY = 300.0
    private lateinit var dino: Sprite
    private var message: Text? = null



    suspend fun init() {
        dino = this.buildDino()

        keys {
            down(Key.SPACE) {
                if (game.status == GameStatus.NOT_STARTED) {
                    game.start()
                }
                if(game.status == GameStatus.FINISHED) {
                    game.restart()
                } else {
                    val dinoY = dino.y
                    if(dinoY >= 200) {
                        status = PlayerStatus.JUMPING_UP
                    }

                }
            }
        }
        dino.onCollision({it.name === "obstacle"}) {
            game.finish()
            stopAnimation()
        }
        dino.addUpdater {
            if(game.isRunning) {
                val coordinates = this@Player.getCoordinates(this.x, this.y)
                dino.position(coordinates.x, coordinates.y)
            }
        }
    }





    private suspend fun buildDino (): Sprite {
        val image = resourcesVfs["dino.png"].readBitmap()
        val walking = SpriteAnimation(
                spriteMap = image,
                spriteWidth = 53,
                spriteHeight = 56,
                columns = 2

        )
        val sprite = sprite(walking)
        sprite.xy(40.0, initialY)

        return sprite
    }

    private fun startAnimation() {
        dino.playAnimationLooped(spriteDisplayTime = TimeSpan(200.0))
        this.message?.removeFromParent()
        this.message = null
    }

    private fun stopAnimation() {
        dino.stopAnimation()
    }

    private fun getCoordinates(startingX: Double, startingY: Double): Coordinates {
        val x: Double = startingX
        var y: Double = startingY

        when (status) {
            PlayerStatus.JUMPING_UP -> {
                y -= 1 * speedFactor
                if(y <= yLimit) {
                    status = PlayerStatus.JUMPING_DOWN
                }
            }

            PlayerStatus.JUMPING_DOWN -> {
                y += 1 * speedFactor
                if(y >= initialY) {
                    status = PlayerStatus.RUNNING
                }
            }
            PlayerStatus.RUNNING -> {
                this.startAnimation()

            }
        }

        return Coordinates(x,y)
    }

}