package advent

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day2Test {
    @Nested
    inner class Part1 {
        @Test
        fun `Should compute score for round 1`() {
            assertEquals(8, Day2.score("A Y"))
        }
        @Test
        fun `Should compute score for round 2`() {
            assertEquals(1, Day2.score("B X"))
        }
        @Test
        fun `Should compute score for round 3`() {
            assertEquals(6, Day2.score("C Z"))
        }
        @Test
        fun `Should compute score for part 1`() {
            assertEquals(10994, Day2.part1())
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Should compute score for round 1`() {
            assertEquals(4, Day2.score2("A Y"))
        }
        @Test
        fun `Should compute score for round 2`() {
            assertEquals(1, Day2.score2("B X"))
        }
        @Test
        fun `Should compute score for round 3`() {
            assertEquals(7, Day2.score2("C Z"))
        }
        @Test
        fun `Should compute score for part 2`() {
            assertEquals(12526, Day2.part2())
        }
    }
}