package advent

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readLines
import kotlin.test.assertEquals

class Day8Test {
    @Nested
    inner class Part1 {
        @Test
        fun `Should count visible trees for sample`() {
            assertEquals(21, Day8.part1(readLines("day8_sample.txt")))
        }

        @Test
        fun `Should count visible trees for input`() {
            assertEquals(1845, Day8.part1(readLines("day8.txt")))
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Should find highest scenic score for sample`() {
            assertEquals(8, Day8.part2(readLines("day8_sample.txt")))
        }

        @Test
        fun `Should find highest scenic score for input`() {
            assertEquals(230112, Day8.part2(readLines("day8.txt")))
        }
    }
}