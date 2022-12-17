package advent

//TODO Optimize
object Day16 {
    fun part1(lines: List<String>): Int {
        val valves = lines.map { parseLine(it) }.associateBy { it.name }
        val shortestPaths = computeShortestPaths(valves)
        return bestPressure(
            listOf(),
            30,
            shortestPaths.keys.filter { v -> valves.getValue(v).flow > 0 },
            valves,
            shortestPaths
        ).values.max()
    }

    fun part2(lines: List<String>): Int {
        val valves = lines.map { parseLine(it) }.associateBy { it.name }
        val shortestPaths = computeShortestPaths(valves)
        val filteredKeys = shortestPaths.keys.filter { v -> valves.getValue(v).flow > 0 }
        val bestHumanPaths = bestPressure(
            listOf(),
            26,
            filteredKeys,
            valves,
            shortestPaths
        )
        return bestHumanPaths.maxOf { (path, bestReleased) ->
            bestReleased + bestPressure(
                listOf(),
                26,
                filteredKeys.filter { !path.contains(it) },
                valves,
                shortestPaths
            ).values.max()
        }
    }

    private fun bestPressure(
        openedValves: List<String>,
        remaining: Int,
        interestingValves: List<String>,
        valves: Map<String, Valve>,
        shortestPaths: Map<String, Map<String, Int>>
    ): Map<List<String>, Int> {
        val currentPos = openedValves.lastOrNull() ?: "AA"
        val paths = shortestPaths.getValue(currentPos)
        val possiblePaths = interestingValves
            .filter { !openedValves.contains(it) }
            .filter { valve -> remaining - paths.getValue(valve) - 1 >= 0 }
        return (possiblePaths.flatMap { valve ->
            val newRemaining = remaining - paths.getValue(valve) - 1
            bestPressure(
                openedValves + valve,
                remaining - paths.getValue(valve) - 1,
                interestingValves,
                valves,
                shortestPaths
            ).map { (path, released) -> path to released + newRemaining * valves.getValue(valve).flow }
        } + (openedValves to 0)).toMap()
    }

    private fun computeShortestPaths(valves: Map<String, Valve>): Map<String, Map<String, Int>> =
        valves.keys.associateWith { key ->
            val map = mutableMapOf<String, Int>()
            val visited = mutableSetOf<String>()
            val toVisit = mutableListOf(key to 0)
            while (toVisit.isNotEmpty()) {
                val (first, steps) = toVisit.removeAt(0)
                if (visited.contains(first)) {
                    continue
                }
                visited.add(first)
                map[first] = steps
                toVisit.addAll(valves[first]!!.tunnels.map { it to steps + 1 })
            }
            map
        }

    private fun parseLine(line: String): Valve {
        val regex = """^Valve (.+) has flow rate=(\d+); tunnels? leads? to valves? (.+)$""".toRegex()
        val (valve, flow, tunnels) = regex.find(line)!!.destructured
        return Valve(valve, flow.toInt(), tunnels.split(", ").toList())
    }
}

data class Valve(val name: String, val flow: Int, val tunnels: List<String>)
