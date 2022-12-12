package advent

object Day12 {
    private val charsHeights = ('a'..'z').toList()
    fun part1(lines: List<String>): Int {
        val (start, end) = startingPositions(lines)
        val heights = toHeightMap(lines)
        return shortestPath(start, end, heights)!!
    }

    fun part2(lines: List<String>): Int {
        val (_, end) = startingPositions(lines)
        val heights = toHeightMap(lines)
        val startingPositions = heights.filter { (_, v) -> v == 'a' }.keys
        return startingPositions.mapNotNull { shortestPath(it, end, heights) }.min()
    }

    private fun shortestPath(
        start: GridPos,
        end: GridPos,
        heights: Map<GridPos, Char>
    ): Int? {
        val visited = mutableSetOf<GridPos>()
        val toVisit = mutableListOf(start to 0)
        while (toVisit.isNotEmpty()) {
            val (current, steps) = toVisit.removeAt(0)
            if (visited.contains(current)) {
                continue
            } else {
                visited.add(current)
            }
            if (current == end) {
                return steps
            }
            val currentHeight = charsHeights.indexOf(heights.getValue(current))
            toVisit.addAll(
                current.neighbors()
                    .filter { heights.keys.contains(it) }
                    .filter { neighbor -> charsHeights.indexOf(heights.getValue(neighbor)) <= currentHeight + 1 }
                    .map { it to steps + 1 }
            )
        }
        return null
    }

    private fun toHeightMap(lines: List<String>): Map<GridPos, Char> = lines.flatMapIndexed { row, line ->
        line.mapIndexed { col, c ->
            GridPos(row, col) to when (c) {
                'S' -> 'a'
                'E' -> 'z'
                else -> c
            }
        }
    }.toMap()

    private fun startingPositions(lines: List<String>): Pair<GridPos, GridPos> {
        var start = GridPos(0, 0)
        var end = GridPos(0, 0)
        for (row in lines.indices) {
            if (lines[row].contains('S')) {
                start = GridPos(row, lines[row].indexOf('S'))
            }
            if (lines[row].contains('E')) {
                end = GridPos(row, lines[row].indexOf('E'))
            }
        }
        return Pair(start, end)
    }
}

data class GridPos(val row: Int, val col: Int) {
    fun neighbors(): Set<GridPos> =
        setOf(GridPos(row - 1, col), GridPos(row + 1, col), GridPos(row, col - 1), GridPos(row, col + 1))
}