package day2

import java.io.File

val scoreMap = mapOf(
    'X' to 1,
    'Y' to 2,
    'Z' to 3
)

fun main() {
    // component1 : A for Rock, B for Paper, and C for Scissors
    // component2 : X for Rock, Y for Paper, and Z for Scissors
    val totalScore = File("src/day2/input.txt")
        .readLines()
        .sumOf {
            val charSequence = it.toCharArray()
            calculateScore(charSequence[0] to charSequence[2])
        }
    println("total score: $totalScore")
}

fun calculateScore(pair: Pair<Char, Char>) : Int = scoreMap[pair.second]?.plus(getResult(pair))!!

// 0 means lose, 3 means draw, 6 means win.
fun getResult(pair: Pair<Char, Char>) = when (pair.first) {
    // rock
    'A' -> when (pair.second) {
        'X' -> 3  // rock
        'Y' -> 6  // paper
        'Z' -> 0  // scissors
        else -> 0 // no sense
    }
    // paper
    'B' -> when (pair.second) {
        'X' -> 0
        'Y' -> 3
        'Z' -> 6
        else -> 0 // no sense
    }
    // scissors
    'C' -> when (pair.second) {
        'X' -> 6
        'Y' -> 0
        'Z' -> 3
        else -> 0 // no sense
    }
    else -> 0
}