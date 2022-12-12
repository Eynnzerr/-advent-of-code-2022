package day11

import java.io.File
import kotlin.math.absoluteValue

fun main() {
    val rounds = 10000

    val monkeys = File("src/day11/input.txt")
        .readLines()
        .split("")
        .map {
            val (items, operation, test, b1, b2) = it.drop(1)
            Monkey(
                items = extractNumbers(items),
                operate = constructActions(operation),
                testNum = extractNumbers(test).first(),
                next = extractNumbers(b1).first().toInt() to extractNumbers(b2).first().toInt()
            )
        }

    val inspectCount = MutableList(monkeys.size) { 0 }

    // for part2:
    val cm = monkeys.map { it.testNum }.reduce { acc, i -> acc lcm i }

    repeat(rounds) {
        monkeys.forEachIndexed { index, monkey ->
            inspectCount[index] += monkey.items.size
            monkey.items.forEach {
                val level = monkey.operate(it) % cm  // The problem says 'rounded down'
                val next = if (level % monkey.testNum == 0L) monkey.next.first else monkey.next.second
                monkeys[next].items.add(level)
            }
            monkey.items.clear()
        }

    }

    inspectCount.sortDescending()
    println("result: ${inspectCount[0].toULong() * inspectCount[1].toULong()}")

}

private fun <T> List<T>.split(vararg delimiters: T): List<List<T>> {
    val result = mutableListOf<List<T>>()
    var lastIndex = 0
    this.forEachIndexed { index, t ->
        if (t in delimiters) {
            result.add(this.subList(lastIndex, index))
            lastIndex = index + 1
        }
    }
    result.add(this.subList(lastIndex, this.size))
    return result
}

private fun extractNumbers(s: String) = Regex("[+-]?\\d+")
    .findAll(s)
    .map { it.value.toLong() }
    .toMutableList()

private fun constructActions(s: String): (Level) -> Level {
    val (operator, operand) = s
        .substringAfter("old ")
        .split(" ")
    return when (operator) {
        "+" -> { old -> old + (operand.toLongOrNull() ?: old) }
        "*" -> { old -> old * (operand.toLongOrNull() ?: old) }
        else -> { old -> old }
    }
}

private infix fun Level.gcd(x: Level): Level = if (x == 0L) this.absoluteValue else x gcd (this % x)
private infix fun Level.lcm(x: Level): Level = (this * x) / (this gcd x)