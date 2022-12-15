package advent

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readLines
import kotlin.test.assertEquals

class Day14Test {

    @Nested
    inner class Part1 {
        @Test
        fun `Should compute sand units before abyss for sample`() {
            assertEquals(24, Day14.part1(readLines("day14_sample.txt")))
        }

        @Test
        fun `Should compute sand units before abyss for input`() {
            assertEquals(745, Day14.part1(readLines("day14.txt")))
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Should compute sand units before abyss for sample`() {
            assertEquals(93, Day14.part2(readLines("day14_sample.txt")))
        }

        @Test
        fun `Should compute sand units before abyss for input`() {
            assertEquals(27551, Day14.part2(readLines("day14.txt")))
        }
    }
}