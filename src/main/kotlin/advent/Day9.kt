package advent

import java.lang.IllegalArgumentException
import kotlin.math.abs

object Day9 {
    fun part1(lines: List<String>): Int {
        var (hx, hy) = 0 to 0
        var (tx, ty) = 0 to 0
        val successivePositions = mutableSetOf(tx to ty)
        lines.forEach { line ->
            val move = parseLine(line)
            repeat(move.count) {
                hx += move.position.x
                hy += move.position.y
                if (abs(hx - tx) >= 2 || abs(hy - ty) >= 2) {
                    tx += hx.compareTo(tx)
                    ty += hy.compareTo(ty)
                    successivePositions.add(tx to ty)
                }
            }
        }
        return successivePositions.size
    }

    private fun parseLine(line: String): Move {
        val (dx, dy) = when (line[0]) {
            'U' -> 0 to -1
            'D' -> 0 to 1
            'L' -> -1 to 0
            'R' -> 1 to 0
            else -> throw IllegalArgumentException("Unknown direction ${line[0]}")
        }
        val count = line.drop(2).toInt()
        return Move(Pos(dx, dy), count)
    }

    fun part2(lines: List<String>): Int {
        val knots = (0 until 10).map { 0 to 0 }.toMutableList()
        val successivePositions = mutableSetOf(0 to 0)
        lines.forEach { line ->
            val move = parseLine(line)
            repeat(move.count) {
                val (hx, hy) = knots[0]
                knots[0] = hx + move.position.x to hy + move.position.y
                for (i in 1..knots.lastIndex) {
                    val (fx, fy) = knots[i - 1]
                    val (sx, sy) = knots[i]
                    if (abs(fx - sx) >= 2 || abs(fy - sy) >= 2) {
                        knots[i] = sx + fx.compareTo(sx) to sy + fy.compareTo(sy)
                    }
                    successivePositions.add(knots[knots.lastIndex])
                }
            }
        }
        return successivePositions.size
    }
}

data class Move(val position: Pos, val count: Int)

data class Pos(val x: Int, val y: Int)