package advent.sponsors

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readLines
import kotlin.test.assertEquals

class MeiliTest {
    @Nested
    inner class Part1 {
        @Test
        fun `Should find kid with fewer stops for sample`() {
            assertEquals("tommy", Meili.part1(readLines("meili_sample.txt")))
        }

        @Test
        fun `Should find kid with fewer stops for input`() {
            assertEquals("bearach", Meili.part1(readLines("meili.txt")))
        }
    }
    @Nested
    inner class Part2 {
        @Test
        fun `Should ??? for sample`() {
            assertEquals(21, Meili.part2(readLines("meili_sample.txt")))
        }

        @Test
        fun `Should ??? for input`() {
            assertEquals(0, Meili.part2(readLines("meili.txt")))
        }
    }
}