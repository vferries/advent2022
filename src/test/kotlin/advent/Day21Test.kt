package advent

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readLines
import kotlin.test.assertEquals

class Day21Test {

    @Nested
    inner class Part1 {
        @Test
        fun `Should correctly compute root for sample`() {
            assertEquals(152, Day21.part1(readLines("day21_sample.txt")))
        }

        @Test
        fun `Should correctly compute root for input`() {
            assertEquals(331319379445180, Day21.part1(readLines("day21.txt")))
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Should find number to yell for sample`() {
            assertEquals(301, Day21.part2(readLines("day21_sample.txt")))
        }

        @Test
        fun `Should find number to yell for input`() {
            assertEquals(3715799488132, Day21.part2(readLines("day21.txt")))
        }
    }
}