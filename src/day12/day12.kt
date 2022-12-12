package day12

import java.io.File
import kotlin.math.min

val matrix = readAsMatrix("src/day12/input.txt")
val start = locate('S', matrix)
val end = locate('E', matrix)

val visited = Array(matrix.size) { BooleanArray(matrix[0].size) { false } }
var result = Int.MAX_VALUE
var steps = 0

fun main() {
    // Obviously a DP problem. dp[i][j] means the minimum cost from start.
    // dp[i][j] = 1 + min(dp[pos in 4 directions && dis <= 1])
    // but will meet problems with iteration
    // so consider another method: backtrack time-consuming!!!
    bfs(start)
    println("fewest steps: $result")
}

private fun bfs(current: Pair<Int, Int>) {
    // update result to find the best solution.
    if (current == end) {
        println("find solution: $steps")
        result = min(result, steps)
        return
    }

    // find valid next hop.
    val next = arrayOf(
        current.first to current.second + 1,
        current.first to current.second - 1,
        current.first + 1 to current.second,
        current.first - 1 to current.second
    ).filter {
        it.first in matrix.indices &&
        it.second in matrix[0].indices &&
        !visited[it.first][it.second] &&
        matrix[current.first][current.second] canReach matrix[it.first][it.second]
    }

    // backtrack.
    for (pos in next){
        visited[pos.first][pos.second] = true
        steps ++

        bfs(pos)

        visited[pos.first][pos.second] = false
        steps --
    }
}

private fun readAsMatrix(src: String) = File(src)
    .readLines()
    .map {
        it.toCharArray()
    }
    .toTypedArray()

private fun locate(target: Char, matrix: Array<CharArray>): Pair<Int, Int> {
    matrix.forEachIndexed { index, chars ->
        val second = chars.indexOf(target)
        if (second != -1) return index to second
    }
    return -1 to -1
}

private fun Char.height() = when (this) {
    'S' -> 0
    'E' -> 25
    else -> this - 'a'
}

private infix fun Char.canReach(next: Char) = this.height() >= next.height() - 1
