package advent

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readLines
import kotlin.test.assertEquals

class Day11Test {
    @Nested
    inner class Part1 {
        @Test
        fun `Should compute level of monkey business for sample`() {
            assertEquals(10605.toBigInteger(), Day11.part1(readLines("day11_sample.txt")))
        }

        @Test
        fun `Should compute level of monkey business input`() {
            assertEquals(95472.toBigInteger(), Day11.part1(readLines("day11.txt")))
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Should compute level of monkey business for sample`() {
            assertEquals(2_713_310_158.toBigInteger(), Day11.part2(readLines("day11_sample.txt")))
        }

        @Test
        fun `Should compute level of monkey business input`() {
            assertEquals(17_926_061_332.toBigInteger(), Day11.part2(readLines("day11.txt")))
        }
    }
}