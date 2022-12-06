package advent

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import readText
import java.util.stream.Stream
import kotlin.test.assertEquals

class Day6Test {
    private companion object {
        @JvmStatic
        fun startOfMessage() = Stream.of(
            Arguments.of(19, "mjqjpqmgbljsphdztnvjfqwrcgsmlb"),
            Arguments.of(23, "bvwbjplbgvbhsrlpgdmjqwftvncz"),
            Arguments.of(23, "nppdvjthqldpwncqszvftbrmjlhg"),
            Arguments.of(29, "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"),
            Arguments.of(26, "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")
        )
    }
        @Nested
    inner class Part1 {
        @Test
        fun `Should find first start of packet marker for sample`() {
            assertEquals(7, Day6.part1("mjqjpqmgbljsphdztnvjfqwrcgsmlb"))
        }

        @Test
        fun `Should find first start of packet marker for input`() {
            assertEquals(1760, Day6.part1(readText("day6.txt")))
        }
    }

    @Nested
    inner class Part2 {
        @ParameterizedTest(name = "Should find {0} as start of message index")
        @MethodSource("advent.Day6Test#startOfMessage")
        fun `Should find correct indices`(expected: Int, input: String) {
            assertEquals(expected, Day6.part2(input))
        }

        @Test
        fun `Should find first start of packet marker for input`() {
            assertEquals(2974, Day6.part2(readText("day6.txt")))
        }
    }
}