package advent

import kotlin.math.ceil
import kotlin.math.max

object Day19 {
    //TODO Optimize (20 minutes)
    fun part1(lines: List<String>): Int {
        val bluePrints = lines.mapIndexed { index, line -> parseBluePrint(index, line) }
        return bluePrints.sumOf { bluePrint -> bluePrint.id * bestScore(bluePrint, 24) }
    }

    fun part2(lines: List<String>): Int {
        val bluePrints = lines.mapIndexed { index, line -> parseBluePrint(index, line) }
        return bluePrints.take(3).map { bluePrint -> bestScore(bluePrint, 32) }.reduce(Int::times)
    }

    fun bestScore(bluePrint: BluePrint, timeRemaining: Int): Int {
        println("Taking a new blueprint ${bluePrint.id}")
        return bestScore(Triple(1, 0, 0), Triple(0, 0, 0), timeRemaining, bluePrint, mutableMapOf())
    }

    private fun bestScore(
        robots: Resources,
        resources: Resources,
        timeRemaining: Int,
        bluePrint: BluePrint,
        memo: MutableMap<Triple<Resources, Resources, Int>, Int>
    ): Int {
        val current = Triple(robots, resources, timeRemaining)
        if (memo.containsKey(current)) {
            return memo.getValue(current)
        }
        if (timeRemaining <= 0) return 0
        // TODO Do not build a robot if max(res) reached
        val robotScores = bluePrint.robots
            .filter { robot ->
                (robot.creationCost.first == 0 || robots.first > 0)
                        && (robot.creationCost.second == 0 || robots.second > 0)
                        && (robot.creationCost.third == 0 || robots.third > 0)
            }
            .map { robot -> robot to robot.stepsToConstruct(robots, resources) }
            .filter { (_, steps) -> steps + 1 <= timeRemaining }
            .map { (robot, stepsNeeded) ->
                if (robot.type == 3) {
                    timeRemaining - stepsNeeded - 1 + bestScore(
                        robots,
                        robot.create(resources).sum(robots * (stepsNeeded + 1)),
                        timeRemaining - stepsNeeded - 1,
                        bluePrint,
                        memo
                    )
                } else {
                    val newRobots = robots.increment(robot.type)
                    bestScore(
                        newRobots,
                        robot.create(resources).sum(robots * (stepsNeeded + 1)),
                        timeRemaining - stepsNeeded - 1,
                        bluePrint,
                        memo
                    )
                }
            }
        val bestScore = robotScores.maxOrNull() ?: 0
        memo[current] = bestScore
        return bestScore
    }

    fun parseBluePrint(index: Int, line: String): BluePrint {
        val bluePrintRegex =
            """Blueprint \d+: Each ore robot costs (\d+) ore. Each clay robot costs (\d+) ore. Each obsidian robot costs (\d+) ore and (\d+) clay. Each geode robot costs (\d+) ore and (\d+) obsidian.""".toRegex()
        val (oreCostOreRobot, oreCostClayRobot, oreCostObsidianRobot, clayCostObsidianRobot, oreCostGeodeRobot, obsidianCostGeodeRobot) = bluePrintRegex.find(
            line
        )!!.destructured
        return BluePrint(
            index + 1, listOf(
                Robot(0, Triple(oreCostOreRobot.toInt(), 0, 0)),
                Robot(1, Triple(oreCostClayRobot.toInt(), 0, 0)),
                Robot(2, Triple(oreCostObsidianRobot.toInt(), clayCostObsidianRobot.toInt(), 0)),
                Robot(3, Triple(oreCostGeodeRobot.toInt(), 0, obsidianCostGeodeRobot.toInt()))
            ).reversed()
        )
    }
}

private operator fun Resources.times(stepsNeeded: Int): Resources =
    Triple(first * stepsNeeded, second * stepsNeeded, third * stepsNeeded)

private fun Resources.increment(type: Int): Resources = when (type) {
    0 -> Triple(first + 1, second, third)
    1 -> Triple(first, second + 1, third)
    2 -> Triple(first, second, third + 1)
    else -> throw IllegalArgumentException("Unsupported increment on type $type")
}

typealias Resources = Triple<Int, Int, Int>

private fun Resources.sum(other: Resources): Resources =
    Triple(this.first + other.first, this.second + other.second, this.third + other.third)

data class Robot(val type: Int, val creationCost: Resources) {

    fun create(resources: Resources): Resources =
        Triple(
            resources.first - creationCost.first,
            resources.second - creationCost.second,
            resources.third - creationCost.third
        )

    fun stepsToConstruct(robots: Resources, resources: Resources): Int {
        var steps = 0
        if (robots.first != 0) {
            steps = max(steps, ceil((creationCost.first - resources.first) / robots.first.toDouble()).toInt())
        }
        if (robots.second != 0) {
            steps = max(steps, ceil((creationCost.second - resources.second) / robots.second.toDouble()).toInt())
        }
        if (robots.third != 0) {
            steps = max(steps, ceil((creationCost.third - resources.third) / robots.third.toDouble()).toInt())
        }
        return steps
    }
}

data class BluePrint(val id: Int, val robots: List<Robot>)
