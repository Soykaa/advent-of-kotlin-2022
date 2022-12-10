data class Quadtuple(
    val a: Pair<Boolean, Int>,
    val b: Pair<Boolean, Int>,
    val c: Pair<Boolean, Int>,
    val d: Pair<Boolean, Int>
)

private fun checkDirection(line: List<Int>, element: Int): Pair<Boolean, Int> {
    var scenicScore = 0
    for (currentElement in line) {
        scenicScore++
        if (currentElement >= element)
            return Pair(false, scenicScore)
    }
    return Pair(true, scenicScore)
}

private fun lookAtDirections(input: List<String>): List<Quadtuple> {
    val inputAsString = input.joinToString("").toList()
    val result = mutableListOf<Quadtuple>()
    (1 until input.size - 1).forEach { i ->
        (1 until input[0].length - 1).forEach { j ->
            val column = inputAsString.slice(j until inputAsString.size step input[i].length)

            val visibilityLeft =
                checkDirection(input[i].slice(0 until j).toList().map { it - '0' }.reversed(), input[i][j] - '0')
            val visibilityRight = checkDirection(
                input[i].slice(j + 1 until input[i].length).toList().map { it - '0' },
                input[i][j] - '0'
            )
            val visibilityTop =
                checkDirection(column.slice(0 until i).map { it - '0' }.reversed(), input[i][j] - '0')
            val visibilityBottom =
                checkDirection(column.slice(i + 1 until column.size).map { it - '0' }, input[i][j] - '0')

            result += Quadtuple(visibilityBottom, visibilityTop, visibilityLeft, visibilityRight)
        }
    }
    return result
}

fun main() {
    fun part1(input: List<String>): Int = lookAtDirections(input).count { (bottom, top, left, right) ->
        bottom.first || left.first || top.first || right.first
    } + input.size * 2 + input[0].length * 2 - 4

    fun part2(input: List<String>): Int = lookAtDirections(input).maxOf { (bottom, top, left, right) ->
        bottom.second * left.second * top.second * right.second
    }

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}