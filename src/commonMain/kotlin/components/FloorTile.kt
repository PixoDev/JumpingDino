package components

import com.soywiz.korge.view.*
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs

class FloorTile(private val startX: Double,private val startY: Double ): Container() {

    suspend fun create(): Image {
        val bitmap = resourcesVfs["floor.png"].readBitmap()
        return image(bitmap).xy(startX, startY)
    }
}