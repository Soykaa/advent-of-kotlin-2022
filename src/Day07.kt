data class Node(var isDir: Boolean, var size: Int, val parent: Node?) {
    var children: MutableMap<String, Node> = mutableMapOf()
}

private val root = Node(true, 0, null)
private fun createFileOrDir(parameter: String, parent: Node?): Node = if (parameter == "dir")
    Node(true, 0, parent)
else {
    Node(false, parameter.toInt(), parent)
}

private fun processCd(cdArg: String, currentNode: Node): Node = when (cdArg) {
    "/" -> root
    ".." -> currentNode.parent ?: currentNode
    else -> currentNode.children[cdArg]!!
}

private fun processFilesAndDirs(arguments: List<String>, currentNode: Node) {
    currentNode.children.putIfAbsent(arguments[1], createFileOrDir(arguments[0], currentNode))
}

private fun calcSize(currentNode: Node): Int {
    for (child in currentNode.children) currentNode.size += calcSize(child.value)
    return currentNode.size
}

private fun init(input: List<String>) {
    var currentNode = root
    for (line in input) when {
        line.contains("\$ cd") -> currentNode = processCd(line.split(" ")[2], currentNode)
        !line.contains("\$ ls") -> processFilesAndDirs(line.split(" "), currentNode)
        else -> {}
    }
    calcSize(root)
}

private fun allDirs(currentNode: Node, sizeSet: MutableSet<Int>): Set<Int> {
    if (currentNode.isDir) sizeSet += currentNode.size
    for (child in currentNode.children.values) sizeSet += allDirs(child, sizeSet)
    return sizeSet
}

private fun totalMost(currentNode: Node, size: Int): Int {
    var currentSize = 0
    if (currentNode.isDir && currentNode.size <= size) currentSize = currentNode.size
    for (child in currentNode.children.values) currentSize += totalMost(child, size)
    return currentSize
}

fun main() {
    fun part1(): Int = totalMost(root, 100000)

    fun part2(): Int =
        allDirs(root, mutableSetOf()).filter { it >= 30000000 - (70000000 - root.size) }.min()

    val input = readInput("Day07")
    init(input)
    println(part1())
//    println(allDirs(root, mutableSetOf()))
    println(part2())
}