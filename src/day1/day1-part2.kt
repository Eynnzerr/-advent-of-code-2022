package day1

import java.io.File
import kotlin.math.max

fun main() {
    val calories = File("src/day1/input.txt").readLines()
    val caloriesPer = mutableListOf<Int>()
    var lastIndex = 0
    calories.forEachIndexed { index, s ->
        if (s == "") {
            caloriesPer.add(calories.subList(lastIndex, index).sumOf { it.toInt() })
            lastIndex = index + 1
        }
    }
    caloriesPer.sortDescending()
    println("top3 most calories:${caloriesPer[0]}, ${caloriesPer[1]}, ${caloriesPer[2]}, sum: ${caloriesPer[0] + caloriesPer[1] + caloriesPer[2]}")
}