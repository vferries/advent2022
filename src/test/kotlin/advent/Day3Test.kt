package advent

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import readLines
import java.util.stream.Stream
import kotlin.test.assertEquals

class Day3Test {
    private companion object {
        @JvmStatic
        fun duplicatesArguments() = Stream.of(
            Arguments.of('p', "vJrwpWtwJgWrhcsFMMfFFhFp"),
            Arguments.of('L', "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"),
            Arguments.of('P', "PmmdzqPrVvPwwTWBwg"),
            Arguments.of('v', "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn"),
            Arguments.of('t', "ttgJtRGJQctTZtZT"),
            Arguments.of('s', "CrZsJsPPZsGzwwsLwLmpwMDw")
        )

        @JvmStatic
        fun inAllGroups() = Stream.of(
            Arguments.of(
                'r', listOf(
                    "vJrwpWtwJgWrhcsFMMfFFhFp",
                    "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
                    "PmmdzqPrVvPwwTWBwg"
                )
            ),
            Arguments.of(
                'Z', listOf(
                    "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
                    "ttgJtRGJQctTZtZT",
                    "CrZsJsPPZsGzwwsLwLmpwMDw"
                )
            )
        )
    }

    @Nested
    inner class Part1 {
        @ParameterizedTest(name = "Should find {0} as duplicate for line {1}")
        @MethodSource("advent.Day3Test#duplicatesArguments")
        fun `Should find duplicates`(expected: Char, input: String) {
            assertEquals(expected, Day3.findDuplicate(input))
        }

        @Test
        fun `Should find correct score for sample`() {
            assertEquals(157, Day3.part1(readLines("day3_sample.txt")))
        }

        @Test
        fun `Should find correct score for input`() {
            assertEquals(8018, Day3.part1(readLines("day3.txt")))
        }
    }

    @Nested
    inner class Part2 {
        @ParameterizedTest(name = "Should find {0} as present in all groups")
        @MethodSource("advent.Day3Test#inAllGroups")
        fun `Should find item in all groups`(expected: Char, input: List<String>) {
            assertEquals(expected, Day3.inAllGroups(input))
        }
        @Test
        fun `Should find correct score for sample`() {
            assertEquals(70, Day3.part2(readLines("day3_sample.txt")))
        }
        @Test
        fun `Should find correct score for input`() {
            assertEquals(2518, Day3.part2(readLines("day3.txt")))
        }
    }
}