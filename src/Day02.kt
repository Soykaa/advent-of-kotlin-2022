fun main() {
    fun moveToPoints(move: String): Int = when (move) {
        "A", "X" -> 1
        "B", "Y" -> 2
        "C", "Z" -> 3
        else -> 0
    }

    fun resToPoints(gameResult: String): Int = when (gameResult) {
        "X" -> 0
        "Y" -> 3
        "Z" -> 6
        else -> 0
    }

    fun currentResult(gameMove: List<Int>): Int = if (gameMove[0] == gameMove[1]) 3
    else if ((gameMove[0] == 2 && gameMove[1] == 1) || (gameMove[0] == 3 && gameMove[1] == 2)
        || (gameMove[0] == 1 && gameMove[1] == 3)
    ) {
        0
    } else {
        6
    }

    fun findSolution(gameMove: List<String>): String =
        if ((gameMove[1] == "X" && gameMove[0] == "A") || (gameMove[1] == "Z" && gameMove[0] == "B")) {
            "C"
        } else if ((gameMove[1] == "X" && gameMove[0] == "B") || (gameMove[1] == "Z" && gameMove[0] == "C")) {
            "A"
        } else if ((gameMove[1] == "X" && gameMove[0] == "C") || (gameMove[1] == "Z" && gameMove[0] == "A")) {
            "B"
        } else {
            gameMove[0]
        }

    fun part1(input: List<String>): Int {
        val gameSum = input
            .map { i -> i.split("\\s".toRegex()).toTypedArray().map { moveToPoints(it) } }
            .sumOf { currentResult(it) + it[1] }
        return gameSum
    }

    fun part2(input: List<String>): Int {
        var gameSum = 0
        for (i in input) {
            val gameMove = i.split("\\s".toRegex()).toTypedArray()
            val ourChoice = findSolution(gameMove.toList())
            gameSum += resToPoints(gameMove[1]) + moveToPoints(ourChoice)
        }
        return gameSum
    }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
