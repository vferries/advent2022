package advent

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readText
import java.math.BigInteger
import kotlin.test.assertEquals

class Day17Test {

    @Nested
    inner class Part1 {
        @Test
        fun `Should compute tower height for sample`() {
            assertEquals(3068, Day17.part1(readText("day17_sample.txt"), 2022))
        }

        @Test
        fun `Should compute tower height for input`() {
            assertEquals(3109, Day17.part1(readText("day17.txt"), 2022))
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Should compute tower height for sample`() {
            assertEquals(BigInteger("1514285714288"), Day17.part2(readText("day17_sample.txt"), BigInteger("1000000000000")))
        }

        @Test
        fun `Should compute tower height for input`() {
            assertEquals(BigInteger("1541449275365"), Day17.part2(readText("day17.txt"), BigInteger("1000000000000")))
        }
    }
}