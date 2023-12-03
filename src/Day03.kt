import javax.swing.tree.TreePath

fun main() {

    fun part1(input: List<String>): Int {
        var result = 0
        val row = input.size
        val col = input[0].length
        val matrix = input.map { it.toList() }

        fun isNearSymbol(i: Int, j: Int): Boolean {
            for (i1 in -1..1) {
                for (j1 in -1..1) {
                    val x = i + i1
                    val y = j + j1
                    if (x in 0 until row && y in 0 until col && !matrix[x][y].isDigit() && matrix[x][y] != '.') {
                        return true
                    }
                }
            }
            return false
        }

        for (i in 0 until row) {
            var j = 0
            while (j < col) {
                val cols = mutableListOf<Int>()
                var number = 0
                while (j < col && matrix[i][j].isDigit()) {
                    cols.add(j)
                    number = number * 10 + (matrix[i][j] - '0')
                    j++
                }
                if (cols.isNotEmpty() && cols.any { isNearSymbol(i, it) }) {
                    result += number
                }

                j++
            }
        }
        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0

        val row = input.size
        val col = input[0].length
        val matrix = input.map { it.toList() }

        fun findNumber(i: Int, j: Int): Triple<Int, Int, Int> {
            var j1 = j
            while (j1 >= 0 && matrix[i][j1].isDigit()) {
                j1--
            }
            j1++
            val start = j1
            var number = 0
            while (j1 < col && matrix[i][j1].isDigit()) {
                number = number * 10 + (matrix[i][j1] - '0')
                j1++
            }
            return Triple(number, i, start)
        }

        fun findNumbers(i: Int, j: Int): List<Int> {
            val numbers = mutableListOf<Int>()
            val map = mutableMapOf<Pair<Int, Int>, Int>()
            for (i1 in -1..1) {
                for (j1 in -1..1) {
                    val x = i + i1
                    val y = j + j1

                    if (x in 0 until row && y in 0 until col && matrix[x][y].isDigit()) {
                        val (number, r, c) = findNumber(x, y)
                        if (!map.containsKey(Pair(r, c))) {
                            map[Pair(r, c)] = 1
                            numbers.add(number)
                        }
                    }
                }
            }
            return numbers
        }

        for (i in 0 until row) {
            for (j in 0 until col) {
                if (matrix[i][j] == '*') {
                    val numbers = findNumbers(i,j)
                    if(numbers.size == 2){
                        result += numbers[0]*numbers[1]
                    }
                }
            }
        }

        return result
    }

    val testInput = readInput("Day03_test")
    part1(testInput).println()
    val input = readInput("Day03")
    part1(input).println()

    val testInput2 = readInput("Day03_test")
    part2(testInput2).println()
    val input2 = readInput("Day03")
    part2(input2).println()
}
