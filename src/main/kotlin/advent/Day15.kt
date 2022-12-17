package advent

import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.math.abs
import kotlin.math.max

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

    fun part2(lines: List<String>, maxValue: Int): Long {
        val beacons = parseBeacons(lines)
        val beaconsPos = beacons.map(Beacon::beaconPos).toSet()
        val result = runBlocking {
            (0..maxValue).chunked(100000).map { ys ->
                val res = ys.map { y ->
                    async {
                        var x = 0
                        while (x <= maxValue) {
                            val currentPos = Pos(x, y)
                            val maxDiff = beacons.maxOf { it.distance - currentPos.manhattan(it.sensorPos) }
                            if (maxDiff >= 0) {
                                x += max(maxDiff, 0) + 1
                            } else if (!beaconsPos.contains(currentPos)) {
                                return@async currentPos.x * 4_000_000.toLong() + currentPos.y
                            }
                        }
                        return@async null
                    }
                }.firstNotNullOfOrNull { it.await() }
                if (res != null) {
                    return@runBlocking res
                }
            }
        }
        return result as Long
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
