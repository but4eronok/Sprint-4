package ru.sber.functional

import kotlin.math.pow

object PowFactory {
    fun buildPowFunction(pow: Int): (Int) -> Int {
        return { num ->
            num.toDouble().pow(pow).toInt()
        }
    }
}
