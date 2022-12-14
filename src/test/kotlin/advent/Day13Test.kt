package advent

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import readLines
import java.util.stream.Stream
import kotlin.test.assertEquals

class Day13Test {
    private companion object {
        @JvmStatic
        fun samples() = Stream.of(
            Arguments.of(true, "[1,1,3,1,1]" to "[1,1,5,1,1]"),
            Arguments.of(true, "[[1],[2,3,4]]" to "[[1],4]"),
            Arguments.of(false, "[9]" to "[[8,7,6]]"),
            Arguments.of(true, "[[4,4],4,4]" to "[[4,4],4,4,4]"),
            Arguments.of(false, "[7,7,7,7]" to "[7,7,7]"),
            Arguments.of(true, "[]" to "[3]"),
            Arguments.of(false, "[[[]]]" to "[[]]"),
            Arguments.of(false, "[1,[2,[3,[4,[5,6,7]]]],8,9]" to "[1,[2,[3,[4,[5,6,0]]]],8,9]")
        )
    }

    @Nested
    inner class Part1 {
        @ParameterizedTest(name = "Should compare pairs {1} from sample")
        @MethodSource("advent.Day13Test#samples")
        fun `Should compare pairs from sample`(result: Boolean, pair: Pair<String, String>) {
            val (left, right) = pair
            assertEquals(
                result,
                Json.decodeFromString<JsonArray>(left).compareTo(Json.decodeFromString<JsonArray>(right)) < 0
            )
        }

        @Test
        fun `Should sum valid indices for sample`() {
            assertEquals(13, Day13.part1(readLines("day13_sample.txt")))
        }

        @Test
        fun `Should sum valid indices for input`() {
            assertEquals(5808, Day13.part1(readLines("day13.txt")))
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Should get decoder key for sample`() {
            assertEquals(140, Day13.part2(readLines("day13_sample.txt")))
        }

        @Test
        fun `Should get decoder key for input`() {
            assertEquals(22713, Day13.part2(readLines("day13.txt")))
        }
    }
}