package advent

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readLines
import kotlin.test.assertEquals

class Day7Test {
    @Nested
    inner class Part1 {
        @Test
        fun `Should find reclaimable size for sample`() {
            assertEquals(95437, Day7.part1(readLines("day7_sample.txt")))
        }

        @Test
        fun `Should find reclaimable size for input`() {
            assertEquals(1989474, Day7.part1(readLines("day7.txt")))
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Should find folder to delete for sample`() {
            assertEquals(24933642, Day7.part2(readLines("day7_sample.txt")))
        }

        @Test
        fun `Should find folder to delete for input`() {
            assertEquals(1111607, Day7.part2(readLines("day7.txt")))
        }

    }
}