package day1

import java.io.File
import kotlin.math.max

fun main() {
    val calories = File("src/day1/input.txt").readLines()
    var lastIndex = 0
    var maxCalories = 0
    calories.forEachIndexed { index, s ->
        if (s == "") {
            maxCalories = max(maxCalories, calories.subList(lastIndex, index).sumOf { it.toInt() })
            lastIndex = index + 1
        }
    }
    println("max calories:$maxCalories")
}