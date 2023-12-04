import kotlin.math.pow

fun main() {
    fun part1(input: List<String>): Int {
        var result = 0
        for (cards in input) {
            val (set1, set2) = cards.substringAfter(":").split("|")
                .map { set -> set.trim().split(" ").filter { it != " " && it.isNotEmpty() }.map { it.toInt() } }
            val count = set1.count { it in set2 }
            result += 2.0.pow(count - 1).toInt()
        }
        return result
    }

    fun part2(input: List<String>): Int {
        val copies = MutableList(input.size) { 1 }
        for (i in input.indices) {
            val cards = input[i]
            val (set1, set2) = cards.substringAfter(":").split("|")
                .map { set -> set.trim().split(" ").filter { it != " " && it.isNotEmpty() }.map { it.toInt() } }
            val count = set1.count { it in set2 }
            repeat(copies[i]) { repeat(count) { copies[i + 1 + it]++ } }
        }

        return copies.sum()
    }

    val testInput = readInput("Day04_test")
    part1(testInput).println()
    val input = readInput("Day04")
    part1(input).println()

    val testInput2 = readInput("Day04_test")
    part2(testInput2).println()
    val input2 = readInput("Day04")
    part2(input2).println()
}
