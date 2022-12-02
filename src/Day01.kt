fun main() {
    fun parseElves(input: List<String>): List<Elf> {
        val elves = mutableListOf(Elf())
        input.forEach { meal ->
            if (meal != "") {
                elves.last().registerMeal(meal.toInt())
            } else {
                // Empty line, add a new Elf
                elves.add(Elf())
            }
        }
        return elves
    }

    fun part1(input: List<String>): Int {
        return parseElves(input).maxOf { it.totalCalories() }
    }

    fun part2(input: List<String>): Int {
        return parseElves(input).map { it.totalCalories() }.sorted().takeLast(3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    checkTest(part1(testInput), 24000)
    checkTest(part2(testInput), 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}

private class Elf {

    val meals = mutableListOf<Int>()

    fun registerMeal(caloriesPerItem: Int) {
        meals.add(caloriesPerItem)
    }

    fun totalCalories(): Int {
        return meals.sum()
    }
}