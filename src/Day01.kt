fun main() {
    fun part1(input: List<String>): Int =
        input.map { it.toList() }.sumOf { list -> (list.first { it.isDigit() } - '0') * 10 + (list.last { it.isDigit() } - '0') }

    fun part2(input: List<String>): Int =
        input.map { it.toList() }.sumOf { list -> (list.first { it.isDigit() } - '0') * 10 + (list.last { it.isDigit() } - '0') }

    val testInput = readInput("Day01_test")
    part1(testInput).println()
    val input = readInput("Day01")
    part2(input).println()
}
