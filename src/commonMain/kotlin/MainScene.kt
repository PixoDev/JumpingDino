import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import components.GameManager
import components.Player
import components.ScoreBoard
import components.World

class MainScene: Scene() {

    override suspend fun Container.sceneInit() {
        val game = GameManager(this)
        val player = Player(game)
        val world = World(game)
        val scoreBoard = ScoreBoard(game)
        addChild(player)
        addChild(world)
        addChild(scoreBoard)
        world.init()
        player.init()
    }
}
