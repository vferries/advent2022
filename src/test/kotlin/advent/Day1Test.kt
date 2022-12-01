package advent

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readText
import kotlin.test.assertEquals

class Day1Test {
    @Nested
    inner class Part1 {
        @Test
        fun `Should compute maximum calories for sample`() {
            assertEquals(24000, Day1.maximumCalories(readText("day1_sample.txt")))
        }

        @Test
        fun `Should compute maximum calories for input`() {
            assertEquals(67633, Day1.maximumCalories(readText("day1.txt")))
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Should compute top 3 calories for sample`() {
            assertEquals(45000, Day1.top3Calories(readText("day1_sample.txt")))
        }

        @Test
        fun `Should compute top 3 calories for input`() {
            assertEquals(199628, Day1.top3Calories(readText("day1.txt")))
        }
    }
}