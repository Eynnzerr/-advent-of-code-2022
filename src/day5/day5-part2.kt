package day5

import java.io.File
import java.util.LinkedList

fun main() {
    val input = File("src/day5/input.txt")
        .readLines()
    val (dividerIndex, stacks) = extract(input)

    val re = Regex("\\d+")
    for (i in dividerIndex + 1 until input.size) {
        val (num, src, dst) = re.findAll(input[i])
            .map { it.value.toInt() }
            .asIterable()
            .toList()

        val temp = LinkedList<Char>()
        repeat(num) {
            temp.addFirst(stacks[src - 1].pollLast())
        }

        stacks[dst - 1].addAll(temp)
    }

    val sb = StringBuilder()
    for (stack in stacks) {
        sb.append(stack.peekLast())
    }
    println("Final result on top: $sb")
}