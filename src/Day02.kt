fun main() {

    val cubesMap = mapOf("blue" to 14, "red" to 12, "green" to 13)

    fun part1(input: List<String>): Int {
        var result = 0
        for(str in input) {
            val (gameString, gamesString) = str.split(":")
            val currentMap = cubesMap.keys.associateWith { 0 }.toMutableMap()
            val gameId = gameString.trim().removePrefix("Game ").trim().toInt()
            gamesString.split(";")
                .map { it.trim() }
                .map { list -> list.split(",")
                    .forEach { val (key,value) = it.trim().split(" ")
                       currentMap[value] = currentMap.getOrDefault(value,0) + key.toInt()
            } }
            if(currentMap.all { it.key in cubesMap && it.value <= cubesMap.getOrDefault(it.key,-1)  }){
                result += gameId
            }
        }
        return result
    }
    fun part2(input: List<String>): Int {
        var result = 0
        for(str in input) {
            val (_, gamesString) = str.split(":")
            val currentMap = cubesMap.keys.associateWith { 0 }.toMutableMap()
            gamesString.split(";")
                .map { it.trim() }
                .map { list ->
                    list.split(",")
                    .forEach { val (key,value) = it.trim().split(" ")
                        currentMap[value] = maxOf(currentMap.getOrDefault(value,0) , key.toInt())
                    }
                }
            result += currentMap.values.reduce { acc, it -> acc*it }
        }

        return result
    }
    val testInput = readInput("Day02_test")
    part1(testInput).println()
    val input = readInput("Day02")
    part1(input).println()

    val testInput2 = readInput("Day02_test")
    part2(testInput2).println()
    val input2 = readInput("Day02")
    part2(input2).println()
}