package advent

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlin.math.min

object Day13 {
    fun part1(lines: List<String>): Int = lines
        .asSequence()
        .filter(String::isNotEmpty)
        .map<String, JsonArray>(Json.Default::decodeFromString)
        .chunked(2)
        .map { (left, right) -> left.compareTo(right) }
        .mapIndexed { index, comparison -> index to comparison }
        .filter { it.second < 0 }
        .sumOf { it.first + 1 }

    fun part2(lines: List<String>): Int {
        val sorted = (lines + "[[2]]" + "[[6]]")
            .filter(String::isNotEmpty)
            .sortedWith(
                Comparator.comparing(
                    { Json.decodeFromString<JsonArray>(it) },
                    { left, right -> left.compareTo(right) })
            )
        return (sorted.indexOf("[[2]]") + 1) * (sorted.indexOf("[[6]]") + 1)
    }
}

private fun JsonPrimitive.compareTo(right: JsonElement): Int = when (right) {
    is JsonArray -> {
        JsonArray(listOf(this)).compareTo(right)
    }
    is JsonPrimitive -> this.int.compareTo(right.int)
    else -> throw IllegalArgumentException("Unknown type")
}

fun JsonArray.compareTo(right: JsonElement): Int {
    when (right) {
        is JsonArray -> {
            for (i in 0 until min(this.size, right.size)) {
                val comparison = this[i].compareTo(right[i])
                if (comparison != 0) {
                    return comparison
                }
            }
            return this.size - right.size
        }
        is JsonPrimitive -> return this.compareTo(JsonArray(listOf(right)))
        else -> throw IllegalArgumentException("Unknown type")
    }
}

private fun JsonElement.compareTo(right: JsonElement): Int {
    return when (this) {
        is JsonArray -> this.compareTo(right)
        is JsonPrimitive -> this.compareTo(right)
        else -> throw IllegalArgumentException("Unknown type ${this.javaClass.name}")
    }
}
