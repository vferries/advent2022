package advent

object Day5 {
    fun part1(lines: List<String>): String {
        val crates = lines.takeWhile { it.isNotEmpty() }
        val stacks = parseStacks(crates)
        val instructions = lines.takeLastWhile { it.isNotEmpty() }.map(::toMove)

        for (instruction in instructions) {
            val from = stacks.getValue(instruction.from)
            val to = stacks.getValue(instruction.to)
            stacks[instruction.from] = from.dropLast(instruction.count)
            stacks[instruction.to] = to + from.takeLast(instruction.count).reversed()
        }

        return stacks.topOfStacks()
    }

    fun part2(lines: List<String>): String {
        val crates = lines.takeWhile { it.isNotEmpty() }
        val stacks = parseStacks(crates)
        val instructions = lines.takeLastWhile { it.isNotEmpty() }.map(::toMove)

        for (instruction in instructions) {
            val from = stacks.getValue(instruction.from)
            val to = stacks.getValue(instruction.to)
            stacks[instruction.from] = from.dropLast(instruction.count)
            stacks[instruction.to] = to + from.takeLast(instruction.count)
        }

        return stacks.topOfStacks()
    }

    private fun parseStacks(crates: List<String>): MutableMap<Int, List<Char>> {
        val stackNames = crates.last()
        val nameIndexes = nameIndexes(stackNames)
        val stacks = nameIndexes.keys.associateWith { listOf<Char>() }.toMutableMap()
        for (line in crates.dropLast(1).reversed()) {
            for (entry in nameIndexes) {
                val c = line.getOrElse(entry.value) { ' ' }
                if (c != ' ') {
                    stacks[entry.key] = stacks.getValue(entry.key) + c
                }
            }
        }
        return stacks
    }

    private fun nameIndexes(stackNames: String): Map<Int, Int> {
        val nameIndexes = mutableMapOf<Int, Int>()
        for (i in stackNames.indices) {
            val c = stackNames[i]
            if (c != ' ') {
                val name = c.toString().toInt()
                nameIndexes[name] = i
            }
        }
        return nameIndexes
    }

    private fun toMove(line: String): Move {
        val (count, from, to) = """move (\d+) from (\d+) to (\d+)""".toRegex().find(line)!!.destructured.toList().map(String::toInt)
        return Move(count, from, to)
    }

    data class Move(val count: Int, val from: Int, val to: Int)
}

private fun Map<Int, List<Char>>.topOfStacks(): String = this.values
    .map(List<Char>::last)
    .joinToString("")
