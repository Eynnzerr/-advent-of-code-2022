package day7

import java.io.File
import java.util.LinkedList

fun main() {
    // First build the tree of file system, then DFS on it.
    val backStack = LinkedList<FileTreeNode>()
    File("src/day7/input.txt")
        .readLines()
        .forEach {
            when {
                it.endsWith('.') -> backStack.pop()  // leave current dir
                it.startsWith("$ cd") -> {
                    // enter new dir. Notice that this directory must exist in current directory, except "/".
                    val dirName = it.substring(5)
                    if (backStack.isEmpty()) {
                        // only happens when pushing root "/" in this problem.
                        backStack.push(FileTreeNode(dirName, true))
                    }
                    else {
                        val parent = backStack.peek()
                        val index = parent.searchChild(dirName)
                        assert(index != -1)
                        backStack.push(parent.children[index])
                    }
                }
                it.startsWith("dir") -> {
                    // find subdirectory under current directory.
                    val dirName = it.substring(4)
                    backStack.peek().children.add(FileTreeNode(dirName, true))
                }
                it == "$ ls" -> {
                    // no action.
                }
                else -> {
                    // find another file under current directory.
                    val info = it.split(" ")
                    backStack.peek().children.add(FileTreeNode(info[1], size = info[0].toInt()))
                }
            }
        }

    // last element of stack must be "/" in this problem.
    val root = backStack.peekLast()
    root.computeSize()
    println("Part1: result: ${FileTreeNode.result1}")

    val totalSize = 70000000
    val neededSize = 30000000

    val remainedSize = totalSize - root.size
    val deleteSize = neededSize - remainedSize
    root.findLeastSize(deleteSize)
    println("Parr2: result: ${FileTreeNode.result2}")
}