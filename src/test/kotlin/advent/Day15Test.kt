package advent

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readLines
import kotlin.test.assertEquals

class Day15Test {

    @Nested
    inner class Part1 {
        @Test
        fun `Should count impossible tiles for line for sample`() {
            assertEquals(26, Day15.part1(readLines("day15_sample.txt"), 10))
        }

        @Test
        fun `Should count impossible tiles for line for input`() {
            assertEquals(5_166_077, Day15.part1(readLines("day15.txt"), 2_000_000))
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Should find beacon tuning frequency for sample`() {
            assertEquals(56_000_011, Day15.part2(readLines("day15_sample.txt"), 20))
        }

        @Test
        fun `Should find beacon tuning frequency for input`() {
            assertEquals(13_071_206_703_981, Day15.part2(readLines("day15.txt"), 4_000_000))
        }
    }
}