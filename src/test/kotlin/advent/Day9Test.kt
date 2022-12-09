package advent

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readLines
import kotlin.test.assertEquals

class Day9Test {
    @Nested
    inner class Part1 {
        @Test
        fun `Should count positions visited by tail for sample`() {
            assertEquals(13, Day9.part1(readLines("day9_sample.txt")))
        }

        @Test
        fun `Should count positions visited by tail for input`() {
            assertEquals(6037, Day9.part1(readLines("day9.txt")))
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Should count positions visited by tail for sample`() {
            assertEquals(36, Day9.part2(readLines("day9_sample2.txt")))
        }

        @Test
        fun `Should count positions visited by tail for input`() {
            assertEquals(2485, Day9.part2(readLines("day9.txt")))
        }
    }
}