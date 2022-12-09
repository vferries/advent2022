package advent

import kotlin.math.max

object Day8 {
    fun part1(lines: List<String>): Int {
        val heights = lines.map { line -> line.toList().map { it.toString().toInt() } }
        val resultArray =
            Array(lines.size) { row ->
                Array(lines[0].length) { col ->
                    row == 0 || col == 0 || row == lines.lastIndex || col == lines[0].lastIndex
                }
            }
        for (row in 1 until lines.lastIndex) {
            var highest = heights[row].first()
            for (col in heights[row].indices) {
                if (heights[row][col] > highest) {
                    resultArray[row][col] = true
                    highest = heights[row][col]
                }
            }
            highest = heights[row].last()
            for (col in heights[row].indices.reversed()) {
                if (heights[row][col] > highest) {
                    resultArray[row][col] = true
                    highest = heights[row][col]
                }
            }
        }
        for (col in 1 until lines[0].lastIndex) {
            var highest = heights[0][col]
            for (row in heights.indices) {
                if (heights[row][col] > highest) {
                    resultArray[row][col] = true
                    highest = heights[row][col]
                }
            }
            highest = heights[heights.lastIndex][col]
            for (row in heights.indices.reversed()) {
                if (heights[row][col] > highest) {
                    resultArray[row][col] = true
                    highest = heights[row][col]
                }
            }
        }
        return resultArray.sumOf { row -> row.map { if (it) 1 else 0 }.sum() }
    }

    fun part2(lines: List<String>): Int {
        val heights = lines.map { line -> line.toList().map { it.toString().toInt() } }
        var maxScore = 0
        for (row in 1 until lines.lastIndex) {
            for (col in 1 until lines[0].lastIndex) {
                val height = heights[row][col]
                val left = heights[row].take(col).reversed()
                val right = heights[row].drop(col + 1)
                val fullColumn = heights.map { line -> line[col] }
                val top = fullColumn.take(row).reversed()
                val bottom = fullColumn.drop(row + 1)
                val score = listOf(left, right, top, bottom)
                    .map { it.treesSeen(height) }
                    .reduce(Int::times)
                maxScore = max(score, maxScore)
            }
        }
        return maxScore
    }
}

private fun List<Int>.treesSeen(height: Int): Int {
    var count = 0
    for (h in this) {
        count++
        if (h >= height) {
            break
        }
    }
    return count
}
