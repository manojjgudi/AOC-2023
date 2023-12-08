fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)

fun main() {
    data class Index(val left: String, val right: String)

    fun part1(input: List<String>): Long {
        val directions = input[0]
        val numberOfDirections = directions.length
        val map = mutableMapOf<String, Index>()
        input
            .filterIndexed { index, _ -> index >= 2 }
            .forEach { str ->
                var (key, value) = str.split("=")
                key = key.trim()
                val (left, right) = value.trim().trim('(').trim(')').split(",").map { it.trim() }
                map[key] = Index(left, right)
            }
        var start = "AAA"
        var numberOfMoves = 0L
        var index = 0
        while (start != "ZZZ") {
            start = if (directions[index] == 'L') map[start]!!.left else map[start]!!.right
            numberOfMoves++
            index = (index + 1) % numberOfDirections
        }
        return numberOfMoves
    }

    fun part2(input: List<String>): Long {
        val directions = input[0]
        val numberOfDirections = directions.length
        val map = mutableMapOf<String, Index>()
        input
            .filterIndexed { index, _ -> index >= 2 }
            .forEach { str ->
                var (key, value) = str.split("=")
                key = key.trim()
                val (left, right) = value.trim().trim('(').trim(')').split(",").map { it.trim() }
                map[key] = Index(left, right)
            }
        val keys = map.keys.filter { it.last() == 'A' }.toMutableList()
        return keys.map { key ->
            var start = key
            var index = 0
            var currentNumberOfMoves = 0L
            while (start.last() != 'Z') {
                start = if (directions[index] == 'L') map[start]!!.left else map[start]!!.right
                currentNumberOfMoves++
                index = (index + 1) % numberOfDirections
            }
            currentNumberOfMoves
        }.reduce { acc, moves -> acc * moves / gcd(acc, moves) }

    }

    val testInput = readInput("Day08_test")
    part1(testInput).println()
    val input = readInput("Day08")
    part1(input).println()

    val testInput2 = readInput("Day08_test")
    part2(testInput2).println()
    val input2 = readInput("Day08")
    part2(input2).println()
}
