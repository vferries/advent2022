package advent

import kotlin.math.abs

object Day18 {
    fun part1(lines: List<String>): Int = exteriorSurface(parseDroplets(lines))

    fun part2(lines: List<String>): Int {
        val droplets = parseDroplets(lines)

        // Bounding box
        val xs = droplets.map { it.x }
        val ys = droplets.map { it.y }
        val zs = droplets.map { it.z }
        val minX = xs.min()
        val maxX = xs.max()
        val minY = ys.min()
        val maxY = ys.max()
        val minZ = zs.min()
        val maxZ = zs.max()

        //List every accessible pos in xs
        val initPos = Pos3D(minX - 1, minY - 1, minZ - 1)
        val toVisit = mutableListOf(initPos)
        val visited = mutableSetOf<Pos3D>()
        while (toVisit.isNotEmpty()) {
            val pos = toVisit.removeAt(0)
            if (visited.contains(pos)) {
                continue
            }
            visited.add(pos)
            toVisit.addAll(
                pos.neighbors()
                    .filter { it.x in (minX - 1)..(maxX + 1) && it.y in (minY - 1)..(maxY + 1) && it.z in (minZ - 1)..(maxZ + 1) }
                    .filter { !droplets.contains(it) }
            )
        }

        // List every possible pos in bounding box
        val allPos =
            (minX..maxX).flatMap { x -> (minY..maxY).flatMap { y -> (minZ..maxZ).map { z -> Pos3D(x, y, z) } } }.toSet()

        val trapped = allPos.subtract(visited).subtract(droplets)
        return part1(lines) - exteriorSurface(trapped)
    }

    private fun exteriorSurface(droplets: Set<Pos3D>) =
        droplets.sumOf { d1 -> 6 - droplets.count { d2 -> d1.manhattan(d2) == 1 } }

    private fun parseDroplets(lines: List<String>): Set<Pos3D> =
        lines.map { line -> line.split(",").map { it.toInt() } }.map { (x, y, z) -> Pos3D(x, y, z) }.toSet()
}

data class Pos3D(val x: Int, val y: Int, val z: Int) {
    fun manhattan(other: Pos3D): Int = abs(x - other.x) + abs(y - other.y) + abs(z - other.z)
    fun neighbors(): List<Pos3D> = listOf(
        Pos3D(x - 1, y, z),
        Pos3D(x + 1, y, z),
        Pos3D(x, y - 1, z),
        Pos3D(x, y + 1, z),
        Pos3D(x, y, z - 1),
        Pos3D(x, y, z + 1)
    )
}
