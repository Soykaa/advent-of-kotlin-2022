import kotlin.math.*

private operator fun Pair<Int, Int>.plus(b: Pair<Int, Int>) = Pair((first + b.first), (second + b.second))

private fun processInstructions(direction: String): Pair<Int, Int>? = when (direction) {
    "R" -> Pair(1, 0)
    "L" -> Pair(-1, 0)
    "U" -> Pair(0, 1)
    "D" -> Pair(0, -1)
    else -> null
}

private fun getDistance(fst: Pair<Int, Int>, snd: Pair<Int, Int>): Int =
    max(abs(fst.first - snd.first), (abs(fst.second - snd.second)))

private fun diagonalStep(rope: MutableList<Pair<Int, Int>>, i: Int): MutableList<Pair<Int, Int>> {
    val visited = mutableListOf<Pair<Int, Int>>()
    while (getDistance(rope[i], rope[i + 1]) > 1) {
        val move = Pair((rope[i].first - rope[i + 1].first).sign, (rope[i].second - rope[i + 1].second).sign)
        rope[i + 1] += move
        visited.add(rope[rope.size - 1])
    }
    return visited
}

private fun countAtLeastOnceVisited(input: List<String>, ropeSize: Int): Int {
    val rope = MutableList(ropeSize) { Pair(0, 0) }
    val visited = mutableListOf<Pair<Int, Int>>()
    input.forEach { line ->
        val (direction, numOfSteps) = line.split(" ")
        repeat(numOfSteps.toInt()) {
            val move = processInstructions(direction)!!
            rope[0] += move
            visited.add(rope[rope.size - 1])
            for (i in 0 until rope.size - 1) visited.addAll(diagonalStep(rope, i))
        }
    }
    return visited.toSet().size
}

fun main() {
    fun part1(input: List<String>): Int = countAtLeastOnceVisited(input, 2)

    fun part2(input: List<String>): Int = countAtLeastOnceVisited(input, 10)

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}
