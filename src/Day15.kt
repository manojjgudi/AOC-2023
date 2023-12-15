fun main() {

    fun String.calculateHash(): Int = this.fold(0) { acc, c -> ((acc + c.code) * 17) % 256 }

    fun part1(input: List<String>): Int = input[0].split(",").sumOf { it.trim().calculateHash() }

    fun part2(input: List<String>): Int {
        val boxes = MutableList<MutableList<Pair<String, Int>>>(256) { mutableListOf() }
        val steps = input[0].split(",").map { it.trim() }
        for (step in steps) {
            if ('-' in step) {
                val label = step.substringBefore("-")
                val hash = label.calculateHash()
                boxes[hash].removeIf { it.first == label }
            } else {
                val (label, focalLength) = step.split("=")
                val hash = label.calculateHash()
                boxes[hash].indexOfFirst { it.first == label }.let {
                    val newPair = Pair(label, focalLength.toInt())
                    if (it == -1) boxes[hash].add(newPair)
                    else boxes[hash][it] = newPair
                }
            }
        }
        return boxes.mapIndexed { index, list ->
            (index + 1) * list.foldIndexed(0) { idx, acc, pair -> acc + (idx + 1) * pair.second }
        }.sum()
    }

    val testInput = readInput("Day15_test")
    val input = readInput("Day15")
    part1(testInput).println()
    part1(input).println()
    part2(testInput).println()
    part2(input).println()

}