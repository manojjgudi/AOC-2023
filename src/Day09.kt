fun main() {
    fun part1(input: List<String>): Long {
        fun backTrack(list: List<Long>): Long =
            if (list.all { it == 0L }) 0 else list.last() + backTrack(list.zipWithNext { i, j -> j - i }.toList())
        return input.map { it.split(" ").map(String::toLong) }.map { backTrack(it) }.reduce { acc, sum -> acc + sum }
    }

    fun part2(input: List<String>): Long {
        fun backTrack(list: List<Long>): Long =
            if (list.all { it == 0L }) 0 else list.first() - backTrack(list.zipWithNext { i, j -> j - i }.toList())
        return input.map { it.split(" ").map(String::toLong) }.map { backTrack(it) }.reduce { acc, sum -> acc + sum }
    }

    val testInput = readInput("Day09_test")
    val input = readInput("Day09")
    part1(testInput).println()
    part1(input).println()
    part2(testInput).println()
    part2(input).println()
}
