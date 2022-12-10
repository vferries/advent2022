package advent

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readLines
import kotlin.test.assertEquals

class Day10Test {
    @Nested
    inner class Part1 {
        @Test
        fun `Should get correct signal strength at different points in time for sample`() {
            val strengths = Day10.computeStrengths(readLines("day10_sample.txt"))
            assertEquals(21, strengths[20])
            assertEquals(19, strengths[60])
            assertEquals(18, strengths[100])
            assertEquals(21, strengths[140])
            assertEquals(16, strengths[180])
            assertEquals(18, strengths[220])

        }

        @Test
        fun `Should sum signal strengths for sample`() {
            assertEquals(13140, Day10.part1(readLines("day10_sample.txt")))
        }

        @Test
        fun `Should sum signal strengths for input`() {
            assertEquals(12640, Day10.part1(readLines("day10.txt")))
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Should draw correct screen for sample`() {
            val expected = """##..##..##..##..##..##..##..##..##..##..
###...###...###...###...###...###...###.
####....####....####....####....####....
#####.....#####.....#####.....#####.....
######......######......######......####
#######.......#######.......#######....."""
            assertEquals(expected, Day10.part2(readLines("day10_sample.txt")))
        }
        @Test
        fun `Should draw correct screen for input`() {
            val expected = """####.#..#.###..####.#....###....##.###..
#....#..#.#..#....#.#....#..#....#.#..#.
###..####.###....#..#....#..#....#.#..#.
#....#..#.#..#..#...#....###.....#.###..
#....#..#.#..#.#....#....#.#..#..#.#.#..
####.#..#.###..####.####.#..#..##..#..#."""
            assertEquals(expected, Day10.part2(readLines("day10.txt")))
        }
    }
}