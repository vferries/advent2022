package advent

object Day6 {
    fun part1(text: String): Int = indexOfStart(text, 4)

    fun part2(text: String): Int = indexOfStart(text, 14)

    private fun indexOfStart(text: String, distinctCharactersCount: Int) = text
        .windowed(distinctCharactersCount)
        .indexOfFirst { block -> block.toSet().size == distinctCharactersCount } + distinctCharactersCount
}