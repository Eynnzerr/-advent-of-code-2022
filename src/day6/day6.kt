package day6

import java.io.File

fun main() {
    val buffer = File("src/day6/input.txt").readText()
    // Find consecutive four distinct letters which indicates the result.
    val hashMap = mutableMapOf(buffer[0] to 0)
    for (i in 1 until buffer.length) {
        if (hashMap.contains(buffer[i])) {
            // Same symbol is found. Remove from map to ensure distinction.
            val m = hashMap[buffer[i]]!!
            with(hashMap.entries.iterator()) {
                while (hasNext()) {
                    val (_, v) = next()
                    if (v <= m) remove()
                }
            }
            hashMap[buffer[i]] = i
        }
        else {
            hashMap[buffer[i]] = i
            // Part two: modify 4 to 14.
            if (hashMap.size == 4) {
                println("result: ${i + 1}, marker: ${hashMap.keys}")
                return
            }
        }
    }
}