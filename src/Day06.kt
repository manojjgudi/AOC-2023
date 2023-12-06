fun main() {

    fun part1(input: List<String>): Long {
        val times =
            input[0].substringAfter(": ").split(" ").map { it.trim() }.filterNot { it.isEmpty() || it.isBlank() }.map { it.toLong() }
        val distances =
            input[1].substringAfter(": ").split(" ").map { it.trim() }.filterNot { it.isEmpty() || it.isBlank() }.map { it.toLong() }

        return times.mapIndexed { index, time ->
            (1..time).fold(0L) { acc, value -> if (distances[index] < (time - value) * value) acc + 1 else acc }
        }.reduce { acc, count -> acc * count }

    }

    fun part2(input: List<String>): Long {
        val time =
            input[0].substringAfter(": ").replace(" ", "").toLong()
        val distance =
            input[1].substringAfter(": ").replace(" ", "").toLong()
        return (1..time).fold(0L) { acc, value -> if (distance < (time - value) * value) acc + 1 else acc }
    }

    val testInput = readInput("Day06_test")
    part1(testInput).println()
    val input = readInput("Day06")
    part1(input).println()

    val testInput2 = readInput("Day06_test")
    part2(testInput2).println()
    val input2 = readInput("Day06")
    part2(input2).println()
}
