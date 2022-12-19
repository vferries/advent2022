package advent

import java.math.BigInteger

object Day17 {
    private val rocks = listOf(
        listOf("####"),
        listOf(
            ".#.",
            "###",
            ".#."
        ),
        listOf(
            "..#",
            "..#",
            "###"
        ),
        listOf(
            "#",
            "#",
            "#",
            "#"
        ),
        listOf(
            "##",
            "##",
        )
    ).map { it.reversed() }.map {rock ->
        rock.indices.flatMap { row -> rock[row].indices.map { col -> Pos(col, row) } }
            .filter { rock[it.y][it.x] == '#' }
    }


    fun part1(jetPattern: String, rocksFallen: Int): Int {
        var currentStep = 0
        var tower = mutableSetOf<Pos>()
        repeat(rocksFallen) { iteration ->
            val rockPositions = rocks[iteration % rocks.size]
            var bottomLeft = Pos(2, (tower.maxOfOrNull { it.y } ?: 0) + 4)
            while (true) {
                //Apply jets
                var direction = when (jetPattern[currentStep++ % jetPattern.length]) {
                    '>' -> Pos(1, 0)
                    '<' -> Pos(-1, 0)
                    else -> throw IllegalArgumentException("Unknown direction")
                }
                if (rockPositions.map { it + bottomLeft + direction }
                        .none { it.x < 0 || it.x > 6 || tower.contains(it) }) {
                    bottomLeft += direction
                }
                //Fall
                direction = Pos(0, -1)
                if (rockPositions.map { it + bottomLeft + direction }
                        .none { it.y <= 0 || tower.contains(it) }) {
                    bottomLeft += direction
                } else {
                    break
                }
            }

            // Insert rockPositions to tower
            tower += rockPositions.map { it + bottomLeft }
        }
        return tower.maxOf { it.y }
    }

    fun part2(jetPattern: String, rocksFallen: BigInteger): BigInteger {
        val rocksSize = rocks.size.toBigInteger()
        val patternSize = jetPattern.length.toBigInteger()
        var currentStep = BigInteger.ZERO
        var removedHeight = BigInteger.ZERO
        var iteration = BigInteger.ZERO
        var tower = mutableSetOf<Pos>()
        val visited = mutableMapOf<Triple<Int, Int, Set<Pos>>, Pair<BigInteger, BigInteger>>()
        while (iteration < rocksFallen) {
            val currentState = Triple((currentStep % patternSize).toInt(), (iteration % rocksSize).toInt(), HashSet(tower))
            val currentHeight = removedHeight + (tower.maxOfOrNull{it.y}?:0).toBigInteger()
            if (visited.containsKey(currentState)) {
                val (oldIter, oldHeight) = visited.getValue(currentState)
                val diffIter = iteration - oldIter
                val diffHeight = currentHeight - oldHeight
                val cycles = (rocksFallen - iteration) / diffIter
                removedHeight += diffHeight * cycles
                iteration += cycles * diffIter
            } else {
                visited[currentState] = iteration to currentHeight
            }

            val rockPositions = rocks[(iteration % rocksSize).toInt()]
            iteration += BigInteger.ONE
            var bottomLeft = Pos(2, (tower.maxOfOrNull { it.y } ?: 0) + 4)
            while (true) {
                //Apply jets
                var direction = when (jetPattern[(currentStep % patternSize).toInt()]) {
                    '>' -> Pos(1, 0)
                    '<' -> Pos(-1, 0)
                    else -> throw IllegalArgumentException("Unknown direction")
                }
                if (rockPositions.map { it + bottomLeft + direction }
                        .none { it.x < 0 || it.x > 6 || tower.contains(it) }) {
                    bottomLeft += direction
                }
                currentStep += BigInteger.ONE
                //Fall
                direction = Pos(0, -1)
                if (rockPositions.map { it + bottomLeft + direction }
                        .none { it.y <= 0 || tower.contains(it) }) {
                    bottomLeft += direction
                } else {
                    break
                }
            }

            tower += rockPositions.map { it + bottomLeft }

            val (removed, newTower) = compactTower(tower)
            removedHeight += removed.toBigInteger()
            tower = newTower
        }
        return removedHeight + tower.maxOf{it.y}.toBigInteger()
    }

    private fun compactTower(
        tower: MutableSet<Pos>
    ): Pair<Int, MutableSet<Pos>> {
        var minHeight = 0
        for (y in tower.maxOf { it.y } downTo tower.minOf { it.y }) {
            if ((0..6).all { tower.contains(Pos(it, y)) || tower.contains(Pos(it, y+1)) }) {
                minHeight = y
                break
            }
        }
        val newTower = if (minHeight > 0) {
            tower
                .filter { it.y > minHeight }
                .map { Pos(it.x, it.y - minHeight) }
                .toMutableSet()
        } else {
            tower
        }
        return Pair(minHeight, newTower)
    }
}