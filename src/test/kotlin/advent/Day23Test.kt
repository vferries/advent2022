package advent

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readLines
import kotlin.test.assertEquals

class Day23Test {

    @Nested
    inner class Part1 {
        @Test
        fun `Should find correct empty positions for sample`() {
            assertEquals(110, Day23.part1(readLines("day23_sample.txt")))
        }

        @Test
        fun `Should find correct empty positions for input`() {
            assertEquals(3990, Day23.part1(readLines("day23.txt")))
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Should find correct round count for sample`() {
            assertEquals(20, Day23.part2(readLines("day23_sample.txt")))
        }

        @Test
        fun `Should find correct round count for input`() {
            assertEquals(1057, Day23.part2(readLines("day23.txt")))
        }
    }
}