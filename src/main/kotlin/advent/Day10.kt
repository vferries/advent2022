package advent

import kotlin.math.abs

object Day10 {
    fun part1(lines: List<String>): Int {
        val strengths = computeStrengths(lines)
        return (20..strengths.size step 40).sumOf { it * strengths[it] }
    }

    fun computeStrengths(lines: List<String>): List<Int> {
        var x = 1
        val strengths = mutableListOf(x, x)
        for (line in lines) {
            strengths += x
            if (line != "noop") {
                val value = line.drop(5).toInt()
                x += value
                strengths += x
            }
        }
        return strengths
    }

    fun part2(lines: List<String>): String {
        val strengths = computeStrengths(lines)
        return (0..5).joinToString("\n") { row ->
            (1..40).joinToString("") { col ->
                val currentX = strengths[row * 40 + col]
                if (abs(col - currentX - 1) <= 1) {
                    "#"
                } else {
                    "."
                }
            }
        }
    }
}