package advent

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readLines
import kotlin.test.assertEquals

class Day19Test {

    @Nested
    inner class Part1 {
        @Test
        fun `Should find max geodes for blueprint 1`() {
            assertEquals(9, Day19.bestScore(Day19.parseBluePrint(0, readLines("day19_sample.txt")[0]), 24))
        }

        @Test
        fun `Should find max geodes for blueprint 2`() {
            assertEquals(12, Day19.bestScore(Day19.parseBluePrint(1, readLines("day19_sample.txt")[1]), 24))
        }

        @Test
        fun `Should find max geodes for sample`() {
            assertEquals(33, Day19.part1(readLines("day19_sample.txt")))
        }

        @Test
        fun `Should find max geodes for input`() {
            assertEquals(1958, Day19.part1(readLines("day19.txt")))
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Should find max geodes for blueprint 1`() {
            assertEquals(56, Day19.bestScore(Day19.parseBluePrint(0, readLines("day19_sample.txt")[0]), 32))
        }

        @Test
        fun `Should find max geodes for blueprint 2`() {
            assertEquals(62, Day19.bestScore(Day19.parseBluePrint(1, readLines("day19_sample.txt")[1]), 32))
        }

        @Test
        fun `Should find max geodes for input`() {
            assertEquals(4257, Day19.part2(readLines("day19.txt")))
        }

        @Test
        fun `Should correctly cout steps needed to construct a robot`() {
            val robot = Robot(0, Triple(4, 0, 0))
            assertEquals(2, robot.stepsToConstruct(Triple(2, 0, 0), Triple(0, 0, 0)))
            assertEquals(0, robot.stepsToConstruct(Triple(1, 0, 0), Triple(6, 0, 0)))
            assertEquals(2, robot.stepsToConstruct(Triple(1, 0, 0), Triple(2, 0, 0)))
        }
    }
}