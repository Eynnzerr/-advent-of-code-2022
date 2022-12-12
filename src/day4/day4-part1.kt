package day4

import java.io.File

fun main() {
    val overLappedCount = File("src/day4/input.txt")
        .readLines()
        .count { isFullyOverlapped(it) }
    println("overlapped count: $overLappedCount")
}

fun isFullyOverlapped(s: String): Boolean {
    // First, parse the string and get two ranges.
    val bounds = s.split("[-,]".toRegex()).map { it.toInt() }
    return (bounds[0] <= bounds[2] && bounds[1] >= bounds[3]) || (bounds[0] >= bounds[2] && bounds[1] <= bounds[3])
}