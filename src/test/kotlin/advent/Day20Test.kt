package advent

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readLines
import kotlin.test.assertEquals

class Day20Test {

    @Nested
    inner class Part1 {
        @Test
        fun `Should sum grove coordinates for sample`() {
            assertEquals(3, Day20.part1(readLines("day20_sample.txt")))
        }

        @Test
        fun `Should sum grove coordinates for input`() {
            assertEquals(7395, Day20.part1(readLines("day20.txt")))
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Should sum grove coordinates for sample`() {
            assertEquals(1623178306, Day20.part2(readLines("day20_sample.txt")))
        }

        @Test
        fun `Should sum grove coordinates for input`() {
            assertEquals(1640221678213, Day20.part2(readLines("day20.txt")))
        }
    }
}