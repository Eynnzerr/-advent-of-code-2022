package day7

class FileTreeNode(
    var name: String = "",
    var isDir: Boolean = false,
    var size: Int = 0 // valid only if not directory
) {
    val children = mutableListOf<FileTreeNode>()  // valid only if not directory

    fun searchChild(name: String): Int {
        var ret = -1
        children.forEachIndexed { idx, child ->
            if (child.name == name && child.isDir) {
                ret = idx
                return@forEachIndexed
            }
        }
        return ret
    }

    // only called once after the tree is built but not been initialized with true size.
    fun computeSize(threshold: Int = 100000): Int {
        if (isDir) {
            val computeResult = children.sumOf { it.computeSize() }
            size = computeResult
            if (size <= threshold) result1 += size
        }
        return size
    }

    fun findLeastSize(threshold: Int) {
        if (size in threshold until result2 && isDir) result2 = size
        for (child in children) child.findLeastSize(threshold)
    }

    companion object {
        var result1: Int = 0
        var result2: Int = Int.MAX_VALUE
    }

}