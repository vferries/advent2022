package advent.sponsors

import java.lang.IllegalArgumentException
import kotlin.math.abs
import readLines

object Infi {
    fun part1(lines: List<String>): Int {
        var direction = Direction.NORTH
        var currentPos = Pos(0, 0)
        for (instruction in lines) {
            val (word, count) = instruction.split(" ")
            when (word) {
                "draai" -> {
                    direction = direction.turn(count.toInt())
                }
                "loop", "spring" -> {
                    currentPos = currentPos.move(count.toInt(), direction)
                }
                else -> throw IllegalArgumentException("Unknown instruction $word")
            }
        }
        return currentPos.manhattan()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        var direction = Direction.NORTH
        var currentPos = Pos(0, 0)
        val allPositions = mutableSetOf(currentPos)
        for (instruction in readLines("infi.txt")) {
            val (word, count) = instruction.split(" ")
            when (word) {
                "draai" -> {
                    direction = direction.turn(count.toInt())
                }
                "loop" -> {
                    repeat(count.toInt()) {
                        currentPos = currentPos.move(1, direction)
                        allPositions.add(currentPos)
                    }
                }
                "spring" -> {
                    currentPos = currentPos.move(count.toInt(), direction)
                    allPositions.add(currentPos)
                }
                else -> throw IllegalArgumentException("Unknown instruction $word")
            }
        }
        println(allPositions)
        val xs = allPositions.map(Pos::x)
        val ys = allPositions.map(Pos::y)
        for (row in ys.min()..ys.max()) {
            for (col in xs.min()..xs.max()) {
                if (allPositions.contains(Pos(col, row))) {
                    print('#')
                } else {
                    print(' ')
                }
            }
            println()
        }
    }
}

enum class Direction {
    NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST, NORTH_WEST;

    fun turn(degrees: Int): Direction {
        val turns = degrees / 45
        val currentIndex = Direction.values().indexOf(this)
        return Direction.values()[(currentIndex + turns + 8) % 8]
    }
}

data class Pos(val x: Int, val y: Int) {
    fun move(steps: Int, direction: Direction): Pos {
        return when (direction) {
            Direction.NORTH -> Pos(x, y - steps)
            Direction.NORTH_EAST -> Pos(x + steps, y - steps)
            Direction.EAST -> Pos(x + steps, y)
            Direction.SOUTH_EAST -> Pos(x + steps, y + steps)
            Direction.SOUTH -> Pos(x, y + steps)
            Direction.SOUTH_WEST -> Pos(x - steps, y + steps)
            Direction.WEST -> Pos(x - steps, y)
            Direction.NORTH_WEST -> Pos(x - steps, y - steps)
        }
    }

    fun manhattan() = abs(x) + abs(y)
}