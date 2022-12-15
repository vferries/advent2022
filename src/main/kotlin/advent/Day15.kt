package advent

import kotlin.math.abs

object Day15 {
    fun part1(lines: List<String>, y: Int): Int {
        val beacons = parseBeacons(lines)
        val beaconsPos = beacons.map(Beacon::beaconPos).toSet()
        var count = 0
        val minX = beacons.minOf { it.sensorPos.x - it.distance }
        val maxX = beacons.maxOf { it.sensorPos.x + it.distance }
        for (x in minX..maxX) {
            val currentPos = Pos(x, y)
            if (!beaconsPos.contains(currentPos) && beacons.any { currentPos.manhattan(it.sensorPos) <= it.distance }) {
                count++
            }
        }
        return count
    }

    fun part2(lines: List<String>, maxValue: Int): Int {
        val beacons = parseBeacons(lines)
        val beaconsPos = beacons.map(Beacon::beaconPos).toSet()
        for (x in 0..maxValue) {
            for (y in 0..maxValue) {
                val currentPos = Pos(x, y)
                if (!beaconsPos.contains(currentPos) && !beacons.any { currentPos.manhattan(it.sensorPos) <= it.distance }) {
                    return currentPos.x * 4_000_000 + currentPos.y
                }
            }
        }
        throw UnsupportedOperationException("Unable to find beacon position")
    }

    private fun parseBeacons(lines: List<String>): List<Beacon> {
        val regex = """Sensor at x=(-?\d+), y=(-?\d+): closest beacon is at x=(-?\d+), y=(-?\d+)""".toRegex()
        val beacons = lines.map { line ->
            val (x1, y1, x2, y2) = regex.find(line)!!.destructured.toList().map(String::toInt)
            val sensorPos = Pos(x1, y1)
            val beaconPos = Pos(x2, y2)
            Beacon(sensorPos, beaconPos, beaconPos.manhattan(sensorPos))
        }
        return beacons
    }
}

data class Beacon(val sensorPos: Pos, val beaconPos: Pos, val distance: Int)

private fun Pos.manhattan(other: Pos): Int = abs(x - other.x) + abs(y - other.y)
