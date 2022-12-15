package advent

import kotlin.math.max
import kotlin.math.min

object Day14 {
    private val sandStart = Pos(500, 0)
    private val destinations = listOf(Pos(0, 1), Pos(-1, 1), Pos(1, 1))
    fun part1(lines: List<String>): Int {
        val rocks = parseRocks(lines)
        val maxY = rocks.maxOf(Pos::y)
        val occupied = rocks.toMutableSet()
        var sandUnits = 0
        while (true) {
            var currentPos = sandStart
            while (true) {
                if (currentPos.y > maxY) {
                    return sandUnits
                }
                val found = destinations
                    .map { currentPos + it }
                    .find { !occupied.contains(it) }
                if (found != null) {
                    currentPos = found
                } else {
                    occupied.add(currentPos)
                    break
                }
            }
            sandUnits++
        }
    }

    fun part2(lines: List<String>): Int {
        val rocks = parseRocks(lines)
        val maxY = rocks.maxOf(Pos::y)
        val occupied = rocks.toMutableSet()
        var sandUnits = 0
        while (true) {
            var currentPos = sandStart
            while (true) {
                val found = destinations
                    .map { currentPos + it }
                    .find { !occupied.contains(it) && it.y < maxY + 2 }
                if (found != null) {
                    currentPos = found
                } else {
                    if (currentPos == sandStart) {
                        return sandUnits + 1
                    }
                    occupied.add(currentPos)
                    break
                }
            }
            sandUnits++
        }
    }

    private fun parseRocks(lines: List<String>): Set<Pos> {
        val rocks = lines.flatMap { line ->
            line
                .split(" -> ")
                .map { it.split(",").map(String::toInt) }
                .map { (x, y) -> Pos(x, y) }
                .windowed(2)
                .flatMap { (p1, p2) ->
                    val minX = min(p1.x, p2.x)
                    val maxX = max(p1.x, p2.x)
                    val minY = min(p1.y, p2.y)
                    val maxY = max(p1.y, p2.y)
                    (minX..maxX).flatMap { x -> (minY..maxY).map { y -> Pos(x, y) } }
                }
        }.toSet()
        return rocks
    }
}

private operator fun Pos.plus(other: Pos): Pos = Pos(this.x + other.x, this.y + other.y)
