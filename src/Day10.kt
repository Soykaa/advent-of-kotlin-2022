private fun getValues(input: List<String>): List<Int> {
    var numOfCycles = 0
    var x = 1
    val xs = mutableListOf<Int>()
    input.forEach { line ->
        numOfCycles++
        xs.add(x)
        if (line.contains("addx")) {
            numOfCycles++
            xs.add(x)
            x += line.split(" ")[1].toInt()
        }
    }
    return xs
}

private fun putPixel(x: Int, pixelPosition: Int): Char = if (pixelPosition % 40 in x - 1..x + 1) '#' else '.'

fun main() {
    fun part1(input: List<String>): Int =
        getValues(input).withIndex().filter { (it.index - 20) % 40 == 0 }.sumOf { (it.index) * it.value }

    //     better visibility
    fun part2(input: List<String>): String =
        getValues(input).withIndex().map { putPixel(it.value, it.index) }.chunked(40)
            .joinToString("\n") { it.toString() }

    val input = readInput("Day10")
    println(part1(input))
    println(part2(input))
}