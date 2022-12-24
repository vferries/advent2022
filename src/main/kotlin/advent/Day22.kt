package advent

import advent.Dir.*

object Day22 {
    fun part1(lines: List<String>): Int {
        val board = parseBoard(lines)
        val startingPos = findStartingPosition(board)
        val instructions = parseInstructions(lines)

        var currentPos = startingPos
        var currentDir = RIGHT
        for (instruction in instructions) {
            if (instruction[0].isDigit()) {
                for (i in 0 until instruction.toInt()) {
                    var nextPos = currentPos + currentDir.offset
                    if (!board.containsKey(nextPos)) {
                        nextPos = wrapAround(currentDir, board, currentPos)
                    }
                    if (board.getValue(nextPos) == '#') {
                        break
                    } else {
                        currentPos = nextPos
                    }
                }
            } else {
                currentDir = when (instruction) {
                    "L" -> currentDir.turnLeft()
                    "R" -> currentDir.turnRight()
                    else -> throw UnsupportedOperationException("Unknown direction $instruction")
                }
            }
        }

        return (currentPos.y + 1) * 1000 + (currentPos.x + 1) * 4 + currentDir.value()
    }

    fun part2(lines: List<String>): Int {
        val board = parseBoard(lines)
        val startingPos = findStartingPosition(board)
        val instructions = parseInstructions(lines)
        val mappings = mutableMapOf<Pair<Pos, Dir>, Pair<Pos, Dir>>()
        for (i in 0 until 50) {
            // Grey
            mappings[Pos(50 + i, 0) to UP] = Pos(0, 150 + i) to RIGHT
            mappings[Pos(0, 150 + i) to LEFT] = Pos(50 + i, 0) to DOWN
            // Orange
            mappings[Pos(100 + i, 0) to UP] = Pos(i, 199) to UP
            mappings[Pos(i, 199) to DOWN] = Pos(100 + i, 0) to DOWN
            // Violet
            mappings[Pos(50, i) to LEFT] = Pos(0, 149 - i) to RIGHT
            mappings[Pos(0, 149 - i) to LEFT] = Pos(50, i) to RIGHT
            // Green
            mappings[Pos(149, i) to RIGHT] = Pos(99, 149 - i) to LEFT
            mappings[Pos(99, 149 - i) to RIGHT] = Pos(149, i) to LEFT
            // Red
            mappings[Pos(50, 50 + i) to LEFT] = Pos(i, 100) to DOWN
            mappings[Pos(i, 100) to UP] = Pos(50, 50 + i) to RIGHT
            // Blue
            mappings[Pos(99, 50 + i) to RIGHT] = Pos(100 + i, 49) to UP
            mappings[Pos(100 + i, 49) to DOWN] = Pos(99, 50 + i) to LEFT
            // Yellow
            mappings[Pos(49, 150 + i) to RIGHT] = Pos(50 + i, 149) to UP
            mappings[Pos(50 + i, 149) to DOWN] = Pos(49, 150 + i) to LEFT
        }

        var currentPos = startingPos
        var currentDir = RIGHT
        for (instruction in instructions) {
            if (instruction[0].isDigit()) {
                for (i in 0 until instruction.toInt()) {
                    if (mappings.containsKey(currentPos to currentDir)) {
                        val (nextPos, nextDir) = mappings.getValue(currentPos to currentDir)
                        if (board.getValue(nextPos) == '#') {
                            break
                        } else {
                            currentPos = nextPos
                            currentDir = nextDir
                        }
                    } else {
                        val nextPos = currentPos + currentDir.offset
                        if (board.getValue(nextPos) == '#') {
                            break
                        } else {
                            currentPos = nextPos
                        }
                    }
                }
            } else {
                currentDir = when (instruction) {
                    "L" -> currentDir.turnLeft()
                    "R" -> currentDir.turnRight()
                    else -> throw UnsupportedOperationException("Unknown direction $instruction")
                }
            }
        }

        return (currentPos.y + 1) * 1000 + (currentPos.x + 1) * 4 + currentDir.value()
    }

    private fun wrapAround(
        currentDir: Dir,
        board: Map<Pos, Char>,
        currentPos: Pos
    ) = when (currentDir) {
        RIGHT -> board.keys.filter { pos -> pos.y == currentPos.y }.minBy { it.x }
        DOWN -> board.keys.filter { pos -> pos.x == currentPos.x }.minBy { it.y }
        LEFT -> board.keys.filter { pos -> pos.y == currentPos.y }.maxBy { it.x }
        UP -> board.keys.filter { pos -> pos.x == currentPos.x }.maxBy { it.y }
    }

    private fun parseInstructions(lines: List<String>): List<String> {
        val regex = """\d+|L|R""".toRegex()
        return regex.findAll(lines.last()).map { it.value }.toList()
    }

    private fun findStartingPosition(board: Map<Pos, Char>): Pos =
        board.keys.filter { pos -> pos.y == 0 }.filter { pos -> board.getValue(pos) != '#' }.minBy { it.x }

    private fun parseBoard(lines: List<String>): Map<Pos, Char> = lines
        .dropLast(2)
        .flatMapIndexed { row, line ->
            line.indices
                .map { col -> Pos(col, row) to lines[row][col] }
        }
        .filter { it.second != ' ' }
        .toMap()
}

enum class Dir(val offset: Pos) {
    RIGHT(Pos(1, 0)), DOWN(Pos(0, 1)), LEFT(Pos(-1, 0)), UP(Pos(0, -1));

    fun value(): Int = Dir.values().indexOf(this)
    fun turnLeft(): Dir =
        when (this) {
            RIGHT -> UP
            DOWN -> RIGHT
            LEFT -> DOWN
            UP -> LEFT
        }

    fun turnRight(): Dir =
        when (this) {
            RIGHT -> DOWN
            DOWN -> LEFT
            LEFT -> UP
            UP -> RIGHT
        }

}
