fun main() {
    Day3().run(
        157,
        70
    )
}

private class Day3 : Day(3) {

    override fun part1(input: List<String>): Int {
        // What is the sum of the priorities of those item types?
        val sharedItemTypes = mutableListOf<ItemType>()
        input.forEach { rucksack ->
            val compartments = getCompartments(rucksack)
            sharedItemTypes.addAll(getSharedItemTypes(compartments))
        }
        return sharedItemTypes.sumOf { getPriority(it) }
    }

    private fun getPriority(itemType: ItemType): Int {
        return if (itemType.isLowerCase()) {
            itemType.code - 96
        } else {
            itemType.code - 38
        }
    }

    private fun getCompartments(rucksack: String): Compartments {
        val middleIndex = rucksack.length / 2
        val firstCompartment = rucksack.substring(0, middleIndex)
        val secondCompartment = rucksack.substring(middleIndex, rucksack.length)
        return Compartments(firstCompartment, secondCompartment)
    }

    private fun getSharedItemTypes(compartments: Compartments): Set<ItemType> {
        return compartments.first.toSet() intersect compartments.second.toSet()
    }

    override fun part2(input: List<String>): Int {
        // Find the item type that corresponds to the badges of each three-Elf group.
        // What is the sum of the priorities of those item types?
        return input.chunked(3) { rucksackGroup -> getCommonItemTypes(rucksackGroup) }
            .map { it.first() }
            .sumOf { getPriority(it) }
    }

    private fun getCommonItemTypes(rucksackGroup: List<String>) =
        rucksackGroup[0].toSet() intersect rucksackGroup[1].toSet() intersect rucksackGroup[2].toSet()

}

private typealias Compartments = Pair<String, String>
private typealias ItemType = Char
