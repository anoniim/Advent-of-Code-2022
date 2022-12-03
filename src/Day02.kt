fun main() {
    Day2().run(
        15,
        12
    )
}

private class Day2 : Day(2) {

    override fun part1(input: List<String>): Int {
        // What would your total score be if everything goes exactly according to your strategy guide?
        return input.sumOf { GameRound(it).getScore() }
    }

    override fun part2(input: List<String>): Int {
        // Following the Elf's instructions for the second column, what would your total score be if everything goes exactly according to your strategy guide?
        return input.sumOf { GameRound(it).getScorePart2() }
    }
}

private class GameRound(val input: String) {

    fun getScore(): Int {
        val shapes = input.split(" ").map { Shape.from(it).score }
        return shapes[1] + resultScore(shapes)
    }

    private fun resultScore(shapes: List<Int>): Int {
        if (shapes[0] == shapes[1]) {
            return 3 // draw
        }
        val result = shapes[0] - shapes[1]
        return if (result == -1 || result == 2) {
            6 // win
        } else {
            0 // loss
        }
    }

    fun getScorePart2(): Int {
        val inputList = input.split(" ")
        val theirShape = Shape.from(inputList[0])
        return resultScorePart2(theirShape.score, inputList[1])
    }

    private fun resultScorePart2(theirShapeScore: Int, end: String): Int {
        return when (end) {
            "X" -> loseShape(theirShapeScore)
            "Y" -> theirShapeScore + 3
            "Z" -> winShape(theirShapeScore) + 6
            else -> throw Exception("invalid input")
        }

    }

    private fun loseShape(theirShapeScore: Int): Int {
        val myShape = theirShapeScore - 1
        return if (myShape == 0) 3 else myShape
    }

    private fun winShape(theirShapeScore: Int): Int {
        val myShape = theirShapeScore + 1
        return if (myShape == 4) 1 else myShape
    }
}

enum class Shape(val score: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    companion object {
        fun from(string: String): Shape {
            return when (string) {
                "A", "X" -> ROCK
                "B", "Y" -> PAPER
                "C", "Z" -> SCISSORS
                else -> throw Exception("invalid input")
            }
        }
    }
}