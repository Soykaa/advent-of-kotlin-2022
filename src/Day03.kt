fun main() {
    fun getPriority(itemType: Char): Int = if (itemType in 'a'..'z')
        itemType - 'a' + 1
    else
        itemType - 'A' + 27

    fun findIntersection(items: String): List<Int> {
        val (fstPart, sndPart) = items.chunked(items.length / 2)
        return fstPart.toSet().intersect(sndPart.toSet()).map { getPriority(it) }
    }

    fun part1(input: List<String>): Int = input.sumOf { line ->
        findIntersection(line).sum()
    }

    fun part2(input: List<String>): Int {
        return input.chunked(3)
            .sumOf {
                it.map { line -> line.toSet() }
                    .reduce { acc, line -> acc.intersect(line) }
                    .sumOf { badge -> getPriority(badge) }
            }
    }

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
