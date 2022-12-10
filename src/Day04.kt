// https://stackoverflow.com/questions/67712063/kotlin-split-string-into-range
private fun IntRange.isSubset(other: IntRange): Boolean = first in other && last in other

private fun IntRange.isOverlap(other: IntRange): Boolean = first in other || last in other

private fun getRanges(input: List<String>): List<List<IntRange>> = input.map { line ->
    line.split(",")
        .map {
            it.split("-")
                .let { (a, b) -> a.toInt()..b.toInt() }
        }
}

fun main() {
    fun part1(input: List<String>): Int = getRanges(input).count { (range1, range2) ->
        range1.isSubset(range2) || range2.isSubset(range1)
    }

    fun part2(input: List<String>): Int = getRanges(input).count { (range1, range2) ->
        range1.isOverlap(range2) || range2.isOverlap(range1)
    }

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
