package day3

import java.io.File

fun main() {
    val rucksacks = File("src/day3/input.txt")
        .readLines()
        .chunked(3)
        .sumOf {
            getPriority(findCommon(findCommon(it[0], it[1]), it[2]).toCharArray()[0]) // equal to distinct()
        }
    println("total priority: $rucksacks")
}

fun findCommon(s1: String, s2: String) = s1.filter { s2.indexOf(it) != -1 }
