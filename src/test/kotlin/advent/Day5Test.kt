package advent

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readLines
import kotlin.test.assertEquals

class Day5Test {
    @Nested
    inner class Part1 {
        @Test
        fun `Should list top of stacks for sample`() {
            assertEquals("CMZ", Day5.part1(readLines("day5_sample.txt")))
        }

        @Test
        fun `Should list top of stacks for input`() {
            assertEquals("DHBJQJCCW", Day5.part1(readLines("day5.txt")))
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Should list top of stacks for sample`() {
            assertEquals("MCD", Day5.part2(readLines("day5_sample.txt")))
        }

        @Test
        fun `Should list top of stacks for input`() {
            assertEquals("WJVRLSJJT", Day5.part2(readLines("day5.txt")))
        }
    }
}