private fun parseStacks(processedInput: MutableList<List<String>>): List<MutableList<Char>> {
    val stacksNumber = processedInput.last().last().last().code
    val stacks: List<MutableList<Char>> = List(stacksNumber) { mutableListOf() }
    processedInput.forEach { line ->
        line.forEachIndexed { i, chunk ->
            run {
                if (chunk[1].isUpperCase())
                    stacks[i].add(chunk[1])
            }
        }
    }
    return stacks.map { it.reversed().toMutableList() }
}

private fun transformInput(input: List<String>): Pair<MutableList<List<String>>, MutableList<String>> {
    val stacksLines = arrayListOf<List<String>>()
    val cmds = arrayListOf<String>()
    var counter = 0
    for (i in input.indices) {
        if (input[i].isEmpty()) {
            counter = i
            break
        }
        stacksLines.add(input[i].chunked(4))
    }
    for (i in counter until input.size) {
        if (input[i].isNotEmpty())
            cmds.add(input[i])
    }
//        println(cmds.size)
    return Pair(stacksLines, cmds)
}

private fun getTopElements(parsedStacks: List<List<Char>>): String =
    parsedStacks.map { it.lastOrNull() ?: "" }.joinToString("")

private fun processCommands(input: List<String>, move: (List<MutableList<Char>>, Int, Int, Int) -> Unit): String {
    val (stacks, cmds) = transformInput(input)
    val parsedStacks = parseStacks(stacks)
    cmds.map { cmd -> cmd.split(" ").filter { it.toIntOrNull() != null } }
        .forEach { move(parsedStacks, it[0].toInt(), it[1].toInt() - 1, it[2].toInt() - 1) }
    return getTopElements(parsedStacks)
}

private fun move1(stacks: List<MutableList<Char>>, amount: Int, from: Int, to: Int) = repeat(amount) {
    stacks[to].add(stacks[from].removeLast())
}

private fun move2(stacks: List<MutableList<Char>>, amount: Int, from: Int, to: Int) {
    stacks[to].addAll(stacks[from].takeLast(amount))
    repeat(amount) {
        stacks[from].removeLast()
    }
}

fun main() {
    fun part1(input: List<String>): String = processCommands(input, ::move1)

    fun part2(input: List<String>): String = processCommands(input, ::move2)

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}