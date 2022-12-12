package day2

import java.io.File

fun main() {
    // component1 : A for Rock, B for Paper, and C for Scissors
    // component2 : X for lose, Y for draw, Z for win
    val totalScore = File("src/day2/input.txt")
        .readLines()
        .sumOf {
            val charSequence = it.toCharArray()
            decode(charSequence[0] to charSequence[2])
        }
    println("totalScore: $totalScore")
}

fun decode(pair: Pair<Char, Char>) : Int{
    return when (pair.first) {
        // rock
        'A' -> when (pair.second) {
            'X' -> 0 + 3 // lose
            'Y' -> 3 + 1  // draw
            'Z' -> 6 + 2  // win
            else -> 0 // no sense
        }
        // paper
        'B' -> when (pair.second) {
            'X' -> 0 + 1
            'Y' -> 3 + 2
            'Z' -> 6 + 3
            else -> 0 // no sense
        }
        // scissors
        'C' -> when (pair.second) {
            'X' -> 0 + 2
            'Y' -> 3 + 3
            'Z' -> 6 + 1
            else -> 0 // no sense
        }
        else -> 0
    }
}