package advent

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readLines
import kotlin.test.assertEquals

class Day4Test {
    @Nested
    inner class Part1 {
        @Test
        fun `Should count how many pairs are fully contained for sample`() {
            assertEquals(2, Day4.part1(readLines("day4_sample.txt")))
        }

        @Test
        fun `Should count how many pairs are fully contained for input`() {
            assertEquals(550, Day4.part1(readLines("day4.txt")))
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Should count how many assignment pairs overlap for sample`() {
            assertEquals(4, Day4.part2(readLines("day4_sample.txt")))
        }

        @Test
        fun `Should count how many assignment pairs overlap for input`() {
            assertEquals(931, Day4.part2(readLines("day4.txt")))
        }
    }
}