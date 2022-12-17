package advent

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readLines
import kotlin.test.assertEquals

class Day16Test {

    @Nested
    inner class Part1 {
        @Test
        fun `Should compute maximum releasable pressure in 30 minutes for sample`() {
            assertEquals(1651, Day16.part1(readLines("day16_sample.txt")))
        }

        @Test
        fun `Should compute maximum releasable pressure in 30 minutes for input`() {
            assertEquals(2183, Day16.part1(readLines("day16.txt")))
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Should compute maximum releasable pressure in 26 minutes with an elephant for sample`() {
            assertEquals(1707, Day16.part2(readLines("day16_sample.txt")))
        }

        @Test
        fun `Should compute maximum releasable pressure in 26 minutes with an elephant for input`() {
            assertEquals(2911, Day16.part2(readLines("day16.txt")))
        }
    }
}