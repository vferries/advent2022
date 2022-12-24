package advent

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readLines
import kotlin.test.Ignore
import kotlin.test.assertEquals

class Day22Test {

    @Nested
    inner class Part1 {
        @Test
        fun `Should find correct ending position for sample`() {
            assertEquals(6032, Day22.part1(readLines("day22_sample.txt")))
        }

        @Test
        fun `Should find correct ending position for input`() {
            assertEquals(165094, Day22.part1(readLines("day22.txt")))
        }
    }

    @Nested
    inner class Part2 {
        @Test
        @Ignore
        fun `Should find correct ending position for sample`() {
            assertEquals(0, Day22.part2(readLines("day22_sample.txt")))
        }

        @Test
        fun `Should find correct ending position for input`() {
            assertEquals(95316, Day22.part2(readLines("day22.txt")))
        }
    }
}