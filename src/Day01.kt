fun main() {
    fun parseElves(input: List<String>): MutableList<Elf> {
        var i = 0
        val elves = mutableListOf(Elf(i))
        input.forEach {
            if (it != "") {
                elves[i].register(it.toInt())
            } else {
                elves.add(Elf(++i))
            }
        }
        return elves
    }

    fun part1(input: List<String>): Int {
        val elves = parseElves(input)
        return elves.maxOfOrNull { it.total() } ?: 0
    }

    fun part2(input: List<String>): Int {
        return parseElves(input).map { it.total() }.sorted().takeLast(3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    val part1 = part1(testInput)
    val part2 = part2(testInput)
    check(part1 == 24000) { "Check failed, wrong result: $part1" }
    check(part2 == 45000) { "Check failed, wrong result: $part2" }

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}

private class Elf(val index: Int) {

    val calories = mutableListOf<Int>()
    fun register(caloriesPerItem: Int) {
        calories.add(caloriesPerItem)
    }

    fun total(): Int {
        return calories.sum()
    }
}