import com.soywiz.korge.*
import com.soywiz.korge.scene.Module
import com.soywiz.korinject.AsyncInjector

suspend fun main() = Korge(Korge.Config(module = MainModule))
object MainModule: Module() {
	override  val mainScene = MainScene::class
	override suspend fun AsyncInjector.configure() {
		mapPrototype { MainScene() }
	}
}