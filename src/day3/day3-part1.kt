package day3

import java.io.File

fun main() {
    val rucksacks = File("src/day3/input.txt")
        .readLines()
        .sumOf { rucksack ->
            // split into 2 substrings, iterate each char in first string and find if it exists in second string
            val s1 = rucksack.substring(0 until rucksack.length / 2)
            val s2 = rucksack.substring(rucksack.length / 2 until rucksack.length)
            for (c1 in s1) {
                if (s2.find { it == c1 } != null) return@sumOf getPriority(c1)
            }
            0
        }
    println("total priority: $rucksacks")
}

fun getPriority(c: Char) = if (c.isUpperCase()) c - 'A' + 27 else c - 'a' + 1