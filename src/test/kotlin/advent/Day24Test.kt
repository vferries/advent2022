package advent

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readLines
import kotlin.test.assertEquals

class Day24Test {

    @Nested
    inner class Part1 {
        @Test
        fun `Should find shortest path through blizzards for sample`() {
            assertEquals(18, Day24.part1(readLines("day24_sample.txt")))
        }

        @Test
        fun `Should find shortest path through blizzards for input`() {
            assertEquals(262, Day24.part1(readLines("day24.txt")))
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Should find shortest path through blizzards for sample`() {
            assertEquals(54, Day24.part2(readLines("day24_sample.txt")))
        }

        @Test
        fun `Should find shortest path through blizzards for input`() {
            assertEquals(785, Day24.part2(readLines("day24.txt")))
        }
    }
}