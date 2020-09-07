package components

import com.soywiz.korge.view.*
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korge.view.image
class Obstacle(private val startX: Double,private val startY: Double ): Container() {

    suspend fun create(): Image {
        val bitmap = resourcesVfs["cactus.png"].readBitmap()
        val img = image(bitmap).xy(startX, startY)
        img.container().name("obstacle")
        return img
    }
}