package day4

import java.io.File

fun main() {
    val overLappedCount = File("src/day4/input.txt")
        .readLines()
        .count { isOverlapped(it) }
    println("overlapped count: $overLappedCount")
}

fun isOverlapped(s: String): Boolean {
    val bounds = s.split("[-,]".toRegex()).map { it.toInt() }
    return (bounds[0]..bounds[1] intersect bounds[2]..bounds[3]).isNotEmpty()  // a little brutal(space costly)
}