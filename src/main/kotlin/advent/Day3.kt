package advent

object Day3 {
    private val scores = ('a'..'z').toList() + ('A'..'Z').toList()
    fun part1(rucksacks: List<String>): Int = rucksacks
        .map(::findDuplicate)
        .sumOf { scores.indexOf(it) + 1 }

    fun part2(rucksacks: List<String>): Int = rucksacks
        .chunked(3)
        .map(::inAllGroups)
        .sumOf { scores.indexOf(it) + 1 }

    fun findDuplicate(rucksack: String): Char {
        val firstHalf = rucksack.take(rucksack.length / 2)
        val secondHalf = rucksack.drop(rucksack.length / 2)
        return firstHalf.first { c -> secondHalf.contains(c) }
    }

    fun inAllGroups(elves: List<String>): Char =
        scores.first { c ->
            elves.all { it.contains(c) }
        }
}