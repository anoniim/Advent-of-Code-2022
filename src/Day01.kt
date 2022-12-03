fun main() {
    Day1().run(
        24000,
        45000
    )
}

private class Day1 : Day(1) {
    override fun part1(input: List<String>): Int {
        // Find the Elf carrying the most Calories. How many total Calories is that Elf carrying?
        return parseElves(input).maxOf { it.totalCalories() }
    }

    override fun part2(input: List<String>): Int {
        // Find the top three Elves carrying the most Calories. How many Calories are those Elves carrying in total?
        return parseElves(input).map { it.totalCalories() }.sorted().takeLast(3).sum()
    }

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