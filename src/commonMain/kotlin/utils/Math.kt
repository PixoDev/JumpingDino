package utils

import kotlin.random.Random.Default.nextDouble

fun randomInRange(min: Double, max:Double): Double {
    return nextDouble() * (max - min) + min
}
