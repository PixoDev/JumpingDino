			import main

object RootGameMain {
	fun runMain() = MyIosGameWindow2.gameWindow.entry { main() }
}

object MyIosGameWindow2 {
	fun setCustomCwd(cwd: String?) = run { com.soywiz.korio.file.std.customCwd = cwd }
	val gameWindow get() = com.soywiz.korgw.MyIosGameWindow
}