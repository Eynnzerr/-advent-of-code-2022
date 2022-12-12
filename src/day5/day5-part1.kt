package day5

import java.io.File
import java.util.LinkedList

fun main() {
    // First, create 3 stacks with given input:
    // Find blank string in list of readLines. the strings above describes how to build the stacks.
    // Parse the last line to get total count of stacks
    // Parse each line from bottom to above to fill the stacks.
    val input = File("src/day5/input.txt")
        .readLines()
    val (dividerIndex, stacks) = extract(input)

    stacks.forEachIndexed { i, stack -> println("No. $i: $stack") }
    // second, parse options using Regex \d+
    val re = Regex("\\d+")
    for (i in dividerIndex + 1 until input.size) {
        val (num, src, dst) = re.findAll(input[i])
            .map { it.value.toInt() }
            .toList()
        repeat(num) {
            stacks[dst - 1].addLast(stacks[src - 1].pollLast())
        }
    }

    val sb = StringBuilder()
    for (stack in stacks) {
        sb.append(stack.peekLast())
    }
    println("Final result on top: $sb")
}

fun extract(input: List<String>): Pair<Int, Array<LinkedList<Char>>> {
    val dividerIndex = input.indexOf("")
    val stackNum = input[dividerIndex - 1].last().digitToInt()
    println("stack count: $stackNum")
    val stacks = Array(stackNum) {
        LinkedList<Char>()
    }
    for (i in 0 until dividerIndex - 1) {
        for (j in 0 until stackNum) {
            if (1 + 4 * j > input[i].length - 1) break
            val c = input[i][1 + 4 * j]
            if (c != ' ') stacks[j].addFirst(c)
        }
    }
    return Pair(dividerIndex, stacks)
}