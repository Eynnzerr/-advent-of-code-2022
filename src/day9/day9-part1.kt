package day9

import java.io.File
import kotlin.math.abs

fun main() {
    // assume H & T overlap at (0, 0) at first.
    var tPos = 0 to 0
    var hPos = 0 to 0

    val visited = mutableSetOf(0 to 0)
    File("src/day9/input.txt")
        .readLines()
        .forEach {
            val movement = it.split(" ")
            repeat(movement[1].toInt()) {
                hPos = when (movement[0]) {
                    "U" -> hPos.first to hPos.second + 1
                    "D" -> hPos.first to hPos.second - 1
                    "L" -> hPos.first - 1 to hPos.second
                    "R" -> hPos.first + 1 to hPos.second
                    else -> 0 to 0  // will not happen
                }
                if (!isAdjacent(tPos, hPos)) {
                    val newX = if (tPos.first < hPos.first) tPos.first + 1
                        else if (tPos.first == hPos.first) tPos.first
                        else tPos.first - 1
                    val newY = if (tPos.second < hPos.second) tPos.second + 1
                    else if (tPos.second == hPos.second) tPos.second
                    else tPos.second - 1

                    tPos = newX to newY
                    visited.add(tPos)
                }
            }
        }

    println("Part1: visited position count: ${visited.size}")
}

fun isAdjacent(x: Pair<Int, Int>, y: Pair<Int, Int>) = (abs(x.first - y.first) <= 1) && (abs(x.second - y.second) <= 1)