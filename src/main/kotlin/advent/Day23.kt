package advent

object Day23 {
    private val order = listOf(
        listOf(ElfPos(-1, -1), ElfPos(0, -1), ElfPos(1, -1)),
        listOf(ElfPos(-1, 1), ElfPos(0, 1), ElfPos(1, 1)),
        listOf(ElfPos(-1, -1), ElfPos(-1, 0), ElfPos(-1, 1)),
        listOf(ElfPos(1, -1), ElfPos(1, 0), ElfPos(1, 1))
    )
    private val neighbors = order.flatten().toSet()

    fun part1(lines: List<String>): Int {
        var elves =
            lines.flatMapIndexed { row, line -> line.mapIndexed { col, c -> if (c == '#') ElfPos(col, row) else null } }
                .filterNotNull()

        repeat(10) { round ->
            val next = mutableListOf<ElfPos>()
            for (elf in elves) {
                var proposed = false
                if (neighbors.none { elves.contains(elf + it) }) {
                    next.add(elf)
                } else {
                    for (i in 0..4) {
                        if (order[(round + i) % order.size].none { elves.contains(elf + it) }) {
                            next.add(elf + order[(round + i) % order.size][1])
                            proposed = true
                            break
                        }
                    }
                    if (!proposed) {
                        next.add(elf)
                    }
                }
            }
            val nextElves = mutableListOf<ElfPos>()
            val collisions = next.filter { next.indexOf(it) != next.lastIndexOf(it) }.toSet()
            for (i in elves.indices) {
                nextElves.add(if (collisions.contains(next[i])) elves[i] else next[i])
            }
            elves = nextElves
        }
        val minX = elves.minOf { it.x }
        val maxX = elves.maxOf { it.x }
        val minY = elves.minOf { it.y }
        val maxY = elves.maxOf { it.y }
        return (maxX - minX + 1) * (maxY - minY + 1) - elves.size
    }

    //TODO Optimize me please
    fun part2(lines: List<String>): Int {
        var elves =
            lines.flatMapIndexed { row, line -> line.mapIndexed { col, c -> if (c == '#') ElfPos(col, row) else null } }
                .filterNotNull()

        var round = 0
        while (true) {
            val next = mutableListOf<ElfPos>()
            for (elf in elves) {
                var proposed = false
                if (neighbors.none { elves.contains(elf + it) }) {
                    next.add(elf)
                } else {
                    for (i in 0..4) {
                        if (order[(round + i) % order.size].none { elves.contains(elf + it) }) {
                            next.add(elf + order[(round + i) % order.size][1])
                            proposed = true
                            break
                        }
                    }
                    if (!proposed) {
                        next.add(elf)
                    }
                }
            }
            val nextElves = mutableListOf<ElfPos>()
            val collisions = next.filter { next.indexOf(it) != next.lastIndexOf(it) }.toSet()
            var moved = false
            for (i in elves.indices) {
                if (collisions.contains(next[i])) {
                    nextElves.add(elves[i])
                } else {
                    nextElves.add(next[i])
                    moved = moved || next[i] != elves[i]
                }
            }
            if (!moved) {
                return round+1
            }
            elves = nextElves
            round++
        }
    }
}

data class ElfPos(val x: Int, val y: Int) {
    operator fun plus(offset: ElfPos): ElfPos = ElfPos(x + offset.x, y + offset.y)
}