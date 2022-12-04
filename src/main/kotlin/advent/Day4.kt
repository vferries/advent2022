package advent

object Day4 {
    fun part1(lines: List<String>): Int = lines.count(::contained)
    fun part2(lines: List<String>): Int = lines.count(::overlap)

    private fun overlap(line: String): Boolean {
        val (x1, x2, y1, y2) = parseInput(line)
        return x2 >= y1 && y2 >= x1
    }

    private fun contained(line: String): Boolean {
        val (x1, x2, y1, y2) = parseInput(line)
        return x1 <= y1 && x2 >= y2 || x1 >= y1 && x2 <= y2
    }

    private fun parseInput(line: String) = line.split(",", "-").map(String::toInt)
}