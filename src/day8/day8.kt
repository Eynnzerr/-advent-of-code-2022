package day8

import java.io.File
import kotlin.math.max

fun main() {
    var count = 0
    var maxScore = 0
    val matrix = File("src/day8/input.txt")
        .readLines()  // List<String>
        .map { s ->
            s.toCharArray().map { c ->
                c.digitToInt()
            }
        }  // List<List<Int>>

    val totalWidth = matrix[0].size
    val totalHeight = matrix.size
    matrix.forEachIndexed { row, list ->
        list.forEachIndexed { col, height ->
            // A tree is visible only if it satisfies at least one of four conditions:
            // taller than matrix[row][0:col-1] or matrix[row][col+1:] or matrix[0:row-1][col] or matrix[row+1:][col]
            var (left, right, top, down) = listOf(true, true, true, true,)
            var (leftScore, rightScore, topScore, downScore) = listOf(0, 0, 0, 0)

            for (pos in col - 1 downTo  0) {
                if (matrix[row][pos] >= height) {
                    left = false
                    leftScore ++
                    break
                }
                else leftScore ++
            }

            for (pos in col + 1 until totalWidth) {
                if (matrix[row][pos] >= height) {
                    right = false
                    rightScore ++
                    break
                }
                else rightScore ++
            }

            for (pos in row - 1 downTo  0) {
                if (matrix[pos][col] >= height) {
                    top = false
                    topScore ++
                    break
                }
                else topScore ++
            }

            for (pos in row + 1 until totalHeight) {
                if (matrix[pos][col] >= height) {
                    down = false
                    downScore ++
                    break
                }
                else downScore ++
            }

            if (left || right || top || down) count ++
            if (row != 0 && row != totalHeight - 1 && col != 0 && col != totalWidth - 1)
                maxScore = max(maxScore, leftScore * rightScore * topScore * downScore)
        }
    }
    // TODO BF algorithm. Need to be improved.
    println("Part1: visible count: $count")
    println("Part2: max visible score: $maxScore")

}