package advent

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readLines
import kotlin.test.assertEquals

class Day18Test {

    @Nested
    inner class Part1 {
        @Test
        fun `Should count lava surface for sample`() {
            assertEquals(64, Day18.part1(readLines("day18_sample.txt")))
        }

        @Test
        fun `Should count lava surface for input`() {
            assertEquals(4390, Day18.part1(readLines("day18.txt")))
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Should count lava surface for sample`() {
            assertEquals(58, Day18.part2(readLines("day18_sample.txt")))
        }

        @Test
        fun `Should count lava surface for input`() {
            assertEquals(2534, Day18.part2(readLines("day18.txt")))
        }
    }
}