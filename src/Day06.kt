private fun indexSearch(input: String, size: Int): Int {
    var start = 0
    var end = size
    while (end <= input.length) {
        if (input.substring(start, end).toSet().size == size) return end
        start += 1
        end += 1
    }
    return -1
}

fun main() {
    fun part1(input: String): Int = indexSearch(input, 4)

    fun part2(input: String): Int = indexSearch(input, 14)

    val input = readInput("Day06")
    println(part1(input[0]))
    println(part2(input[0]))
}
