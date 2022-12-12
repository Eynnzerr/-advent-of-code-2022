package day9

import java.io.File

fun main() {
    // assume H & T overlap at (0, 0) at first.
    val ropeLen = 10
    val positions = Array(ropeLen) { 0 to 0 }

    val visited = mutableSetOf(0 to 0)
    File("src/day9/input.txt")
        .readLines()
        .forEach {
            val movement = it.split(" ")
            repeat(movement[1].toInt()) {
                positions[0] = when (movement[0]) {
                    "U" -> positions[0].first to positions[0].second + 1
                    "D" -> positions[0].first to positions[0].second - 1
                    "L" -> positions[0].first - 1 to positions[0].second
                    "R" -> positions[0].first + 1 to positions[0].second
                    else -> 0 to 0  // will not happen
                }

                for (i in 1 until ropeLen) {
                    if (!isAdjacent(positions[i], positions[i-1])) {
                        val newX = if (positions[i].first < positions[i-1].first) positions[i].first + 1
                        else if (positions[i].first == positions[i-1].first) positions[i].first
                        else positions[i].first - 1
                        val newY = if (positions[i].second < positions[i-1].second) positions[i].second + 1
                        else if (positions[i].second == positions[i-1].second) positions[i].second
                        else positions[i].second - 1

                        positions[i] = newX to newY
                        if (i == ropeLen - 1) visited.add(positions[i])
                    }
                }

            }
        }

    println("Part1: visited position count: ${visited.size}")
}