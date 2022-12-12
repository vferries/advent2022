package advent

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readLines
import kotlin.test.assertEquals

class Day12Test {
    @Nested
    inner class Part1 {
        @Test
        fun `Should compute level of monkey business for sample`() {
            assertEquals(31, Day12.part1(readLines("day12_sample.txt")))
        }

        @Test
        fun `Should compute level of monkey business input`() {
            assertEquals(361, Day12.part1(readLines("day12.txt")))
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Should compute level of monkey business for sample`() {
            assertEquals(29, Day12.part2(readLines("day12_sample.txt")))
        }

        @Test
        fun `Should compute level of monkey business input`() {
            assertEquals(354, Day12.part2(readLines("day12.txt")))
        }
    }
}