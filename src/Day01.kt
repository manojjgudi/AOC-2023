fun main() {
    val validIntegers = mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9,
        "1" to 1,
        "2" to 2,
        "3" to 3,
        "4" to 4,
        "5" to 5,
        "6" to 6,
        "7" to 7,
        "8" to 8,
        "9" to 9
    )

    fun part1(input: List<String>): Int =
        input.map { it.toList() }.sumOf { list -> (list.first { it.isDigit() } - '0') * 10 + (list.last { it.isDigit() } - '0') }

    fun part2(input: List<String>): Int {
        var result = 0
        for (str in input) {
            val requiredSum = validIntegers.map { validInteger -> validInteger.value to str.indexOf(validInteger.key)  }.filter { it.second != -1 }.minBy { it.second }.first*10 + validIntegers.map { validInteger -> validInteger.value to str.lastIndexOf(validInteger.key)  }.filter { it.second != -1 }.maxBy { it.second }.first
            result += requiredSum
        }
        return result
    }


    val testInput = readInput("Day01_test")
    part1(testInput).println()
    val input = readInput("Day01")
    part1(input).println()

    val testInput2 = readInput("Day01_test2")
    part2(testInput2).println()
    val input2 = readInput("Day01")
    part2(input2).println()
}
