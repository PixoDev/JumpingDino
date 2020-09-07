package components

import com.soywiz.klock.TimeSpan
import com.soywiz.klock.timesPerSecond
import com.soywiz.korge.view.*
import com.soywiz.korio.async.delay
import constants.GameStatus

class ScoreBoard(private val game: GameManager): Container() {
    private var score: Int = 0;
    private var scoreView: Text = text("SCORE $score").xy(10.0,10.0)
    init {
        addChild(scoreView)
        addFixedUpdater(2.timesPerSecond) {
            if(game.status == GameStatus.RESTARTED) {
                restartScore()
            }
            else if(game.isRunning) {
                updateScore()
            }
        }
    }

    private fun updateScore() {
        score++
        scoreView.text = "SCORE $score"
    }

    private fun restartScore() {
        score = 0
        scoreView.text = "SCORE $score"
    }
}