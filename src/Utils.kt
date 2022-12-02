import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt")
    .readLines()

fun readInts(name: String) = readInput(name).map { it.toInt() }

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

abstract class Day(private val dayNumber: Int) {
    abstract fun part1(input: List<String>): Int
    abstract fun part2(input: List<String>): Int

    fun run() {
        // test if implementation meets criteria from the description, like:
        val testInput = readInput("Day${dayNumber}_test")
        checkTest(part1(testInput), 24000)
        checkTest(part2(testInput), 45000)

        val input = readInput("Day$dayNumber")
        println("Part 1 result: ${part1(input)}")
        println("Part 2 result: ${part2(input)}")
    }

    private fun checkTest(result: Int, expected: Int) {
        check(result == expected) { "Test check failed, wrong result: $result" }
    }
}
