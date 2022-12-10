private fun transformInput(input: List<String>): List<Int> {
    val calories: MutableList<Int> = mutableListOf()
    val caloriesBlock: MutableList<Int> = mutableListOf()

    for (line in input) {
        if (line.isEmpty()) {
            calories += caloriesBlock.sum()
            caloriesBlock.clear()
        } else caloriesBlock += line.toInt()
    }
    if (caloriesBlock.isNotEmpty()) calories += caloriesBlock
    return calories
}

fun main() {
    fun part1(input: List<String>): Int = transformInput(input).max()

    fun part2(input: List<String>): Int = transformInput(input).sortedDescending().take(3).sum()

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
