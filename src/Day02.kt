fun main() {
    Day2().run(
        15,
        -1
    )
}

private class Day2 : Day(2) {

    override fun part1(input: List<String>): Int {
        // What would your total score be if everything goes exactly according to your strategy guide?
        return input.sumOf { GameRound(it).getScore() }
    }

    override fun part2(input: List<String>): Int {
        // Following the Elf's instructions for the second column, what would your total score be if everything goes exactly according to your strategy guide?
        return -1
    }
}

private class GameRound(val input: String) {

    fun getScore(): Int {
        val shapes = input.split(" ").map { Shape.from(it).score }
        return shapes[1] + resultScore(shapes)
    }

    private fun resultScore(shapes: List<Int>): Int {
        if (shapes[0] == shapes[1]) {
            // draw
            return 3
        }
        val result = shapes[0] - shapes[1]
        return if (result == -1 || result == 2) {
            // win
            6
        } else {
            // loss
            0
        }
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