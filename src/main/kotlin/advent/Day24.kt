package advent

import java.util.*
import kotlin.math.abs
import kotlin.math.max

object Day24 {
    fun part1(lines: List<String>): Int {
        val start = GridPos(0, lines.first().indexOf('.'))
        val goal = GridPos(lines.lastIndex, lines.last().indexOf('.'))
        val blizzards = generateBlizzards(lines)
        return aStar(start, goal, blizzards, 0)
    }

    fun part2(lines: List<String>): Int {
        val start = GridPos(0, lines.first().indexOf('.'))
        val goal = GridPos(lines.lastIndex, lines.last().indexOf('.'))
        val blizzards = generateBlizzards(lines)
        val phase1 = aStar(start, goal, blizzards)
        val phase2 = aStar(goal, start, blizzards, phase1)
        val phase3 = aStar(start, goal, blizzards, phase1 + phase2)
        return phase1 + phase2 + phase3
    }

    private fun generateBlizzards(lines: List<String>): List<Set<GridPos>> {
        val allPos = lines.flatMapIndexed { row, line -> line.indices.map { GridPos(row, it) } }
        val left = allPos.filter { pos -> lines[pos.row][pos.col] == '<' }.map { it to GridPos(0, -1) }
        val right = allPos.filter { pos -> lines[pos.row][pos.col] == '>' }.map { it to GridPos(0, 1) }
        val up = allPos.filter { pos -> lines[pos.row][pos.col] == '^' }.map { it to GridPos(-1, 0) }
        val down = allPos.filter { pos -> lines[pos.row][pos.col] == 'v' }.map { it to GridPos(1, 0) }
        val winds = left + right + up + down
        val walls = allPos.filter { pos -> lines[pos.row][pos.col] == '#' }
        val width = lines[0].length - 2
        val height = lines.size - 2
        val windSteps = mutableListOf<Set<GridPos>>()
        var currentWinds = winds
        repeat(lcm(width, height)) {
            currentWinds = currentWinds.map { (p, offset) ->
                val (r, c) = p
                val (or, oc) = offset
                val dest = GridPos(((r + or - 1 + height) % height) + 1, ((c + oc - 1 + width) % width) + 1)
                dest to offset
            }
            windSteps += (currentWinds.map { it.first } + walls).toSet()
        }
        return windSteps
    }

    private fun lcm(a: Int, b: Int): Int = a * (b / gcd(a, b))

    private fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

    private fun aStar(start: GridPos, goal: GridPos, blizzards: List<Set<GridPos>>, offset: Int = 0): Int {
        val h = { pos: GridPos -> pos.manhattan(goal) }
        val cameFrom = mutableMapOf<PosWithSteps, PosWithSteps>()
        val gScore = mutableMapOf(PosWithSteps(start, 0) to 0)
        val fScore = mutableMapOf(PosWithSteps(start, 0) to h(start))
        val openSet = PriorityQueue<PosWithSteps>(compareBy { fScore.getOrDefault(it, Integer.MAX_VALUE) })
        openSet.add(PosWithSteps(start, 0))
        while (openSet.isNotEmpty()) {
            val top = openSet.poll()
            val current = top.pos
            val steps = top.steps
            if (current == goal) {
                return reconstructPath(cameFrom, top)
            }
            val neighbors = (current.neighbors() + current)
                .filter { it.row >= 0 && it.row <= max(goal.row, start.row) }
                .filter { !blizzards[(steps + offset) % blizzards.size].contains(it) }
            for (neighbor in neighbors) {
                val n = PosWithSteps(neighbor, steps + 1)
                val tentativeGScore = gScore.getOrDefault(top, Int.MAX_VALUE) + 1
                if (tentativeGScore < gScore.getOrDefault(n, Int.MAX_VALUE)) {
                    cameFrom[n] = top
                    gScore[n] = tentativeGScore
                    fScore[n] = tentativeGScore + h(neighbor)
                    if (!openSet.contains(n)) {
                        openSet.add(n)
                    }
                }
            }
        }
        throw UnsupportedOperationException("404 - Not found")
    }

    private fun <T> reconstructPath(cameFrom: Map<T, T>, current: T): Int {
        var steps = 0
        var pos = current
        while (cameFrom.containsKey(pos)) {
            pos = cameFrom.getValue(pos)
            steps++
        }
        return steps
    }
}

private operator fun GridPos.plus(other: GridPos): GridPos = GridPos(this.row + other.row, this.col + other.col)

data class PosWithSteps(val pos: GridPos, val steps: Int)

private fun GridPos.manhattan(other: GridPos): Int =
    abs(this.row - other.row) + abs(this.col - other.col)
