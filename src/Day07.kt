fun main() {

    fun isFiveOfAKind(set: String): Boolean = set.toSet().size == 1

    fun isFourOfAKind(set: String): Boolean =
        set.groupBy { it }.any { it.value.size == 4 }

    fun isFullHouse(set: String): Boolean =
        set.groupBy { it }.any { it.value.size == 3 } && set.groupBy { it }.size == 2

    fun isThreeOfAKind(set: String): Boolean =
        set.groupBy { it }.any { it.value.size == 3 }

    fun isTwoPair(set: String): Boolean =
        set.groupBy { it }.count { it.value.size == 2 } == 2

    fun isOnePair(set: String): Boolean =
        set.groupBy { it }.any { it.value.size == 2 }

    fun getSetRank(set: String): Int = when {
        isFiveOfAKind(set) -> 6
        isFourOfAKind(set) -> 5
        isFullHouse(set) -> 4
        isThreeOfAKind(set) -> 3
        isTwoPair(set) -> 2
        isOnePair(set) -> 1
        else -> 0
    }

    fun getCardRank(card: Char): Int = when (card) {
        'A' -> 14
        'K' -> 13
        'Q' -> 12
        'J' -> 11
        'T' -> 10
        else -> Character.getNumericValue(card)
    }

    fun getCardRankPart2(card: Char): Int = when (card) {
        'A' -> 14
        'K' -> 13
        'Q' -> 12
        'J' -> 0
        'T' -> 10
        else -> Character.getNumericValue(card)
    }

    fun compareCardByRank(card1: String, card2: String): Int {
        for (i in card1.indices) {
            val rank1 = getCardRank(card1[i])
            val rank2 = getCardRank(card2[i])
            return if (rank1 == rank2)
                continue
            else if (rank1 > rank2) {
                1
            } else {
                -1
            }
        }
        return 0
    }

    fun compareCardByRankPart2(card1: String, card2: String): Int {
        for (i in card1.indices) {
            val rank1 = getCardRankPart2(card1[i])
            val rank2 = getCardRankPart2(card2[i])
            return if (rank1 == rank2)
                continue
            else if (rank1 > rank2) {
                1
            } else {
                -1
            }
        }
        return 0
    }


    fun part1(input: List<String>): Long {
        val cardsList = input.map {
            val (card, bid) = it.split(" ")
            card to bid.toLong()
        }
        val compareSets = Comparator<Pair<String, Long>> { (card1, _), (card2, _) ->

            val setTypeComparison = getSetRank(card1) - getSetRank(card2)
            when {
                setTypeComparison != 0 -> setTypeComparison
                else -> {
                    val res = compareCardByRankPart2(card1, card2)
                    res
                }
            }
        }
        return cardsList.sortedWith(compareSets).mapIndexed { index, pair -> pair.second * (index + 1) }.sumOf { it }
    }

    fun part2(input: List<String>): Long {
        val cardsList = input.map {
            val (card, bid) = it.split(" ")
            card to bid.toLong()
        }
        val compareSets = Comparator<Pair<String, Long>> { (card1, _), (card2, _) ->

            val setTypeComparison: Int
            if (card1.contains('J') || card2.contains('J')) {
                val topcards1 = card1.groupBy { it }.entries.sortedWith(compareByDescending { it.value.size }).map { it.key }
                var charToReplace1 = topcards1[0]
                if (topcards1.size > 1 && topcards1[0] == 'J') {
                    charToReplace1 = topcards1[1]
                }

                val topcards2 = card2.groupBy { it }.entries.sortedWith(compareByDescending { it.value.size }).map { it.key }
                var charToReplace2 = topcards2[0]
                if (topcards2.size > 1 && topcards2[0] == 'J') {
                    charToReplace2 = topcards2[1]
                }

                val newCard1 = card1.replace('J', charToReplace1)
                val newCard2 = card2.replace('J', charToReplace2)
                setTypeComparison = getSetRank(newCard1) - getSetRank(newCard2)

            } else {
                setTypeComparison = getSetRank(card1) - getSetRank(card2)
            }
            when {
                setTypeComparison != 0 -> setTypeComparison
                else -> {
                    val res = compareCardByRankPart2(card1, card2)
                    res
                }
            }
        }
        return cardsList.sortedWith(compareSets).mapIndexed { index, pair -> pair.second * (index + 1) }.sumOf { it }
    }

    val testInput = readInput("Day07_test")
    part1(testInput).println()
    val input = readInput("Day07")
    part1(input).println()

    val testInput2 = readInput("Day07_test")
    part2(testInput2).println()
    val input2 = readInput("Day07")
    part2(input2).println()
}