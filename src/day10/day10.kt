package day10

import java.io.File
import kotlin.math.abs

var cycle = 0
var register = 1
var result = 0
val target = arrayOf(20, 60, 100, 140, 180, 220)

fun main() {
    File("src/day10/input.txt")
        .readLines()
        .forEach {
            if (it == "noop")
                increment()
            else {
                repeat(2) { increment() }
                register += it.split(" ")[1].toInt()
            }
        }
    println("\nPart1: $result. Spend cycle: $cycle")
}

fun increment() {
    // for part2  answer: ZUPRFECL
    if (cycle % 40 == 0) println()
    print(if (abs(cycle % 40 - register) <= 1) "#" else ".")

    cycle ++

    // for part1
    if (cycle in target) result += cycle * register
}