package advent

import readLines
import java.lang.IllegalArgumentException

object Day2 {
    private val otherScores = listOf("A", "B", "C")
    private val ourScores = listOf("X", "Y", "Z")
    fun score(round: String): Int {
        val (opponent, us) = round.split(" ")
        return ourScores.indexOf(us) + 1 + comparison(opponent, us)
    }

    fun score2(round: String): Int {
        val (opponent, expectedResult) = round.split(" ")
        return 1 + when (expectedResult) {
            "X" -> 0 + (otherScores.indexOf(opponent) + 2) % 3
            "Y" -> 3 + otherScores.indexOf(opponent)
            "Z" -> 6 + (otherScores.indexOf(opponent) + 1) % 3
            else -> throw IllegalArgumentException("Unknown result $expectedResult")
        }
    }

    private fun comparison(opponent: String, us: String): Int {
        val oIndex = otherScores.indexOf(opponent)
        val myIndex = ourScores.indexOf(us)
        return when {
            oIndex == myIndex -> 3
            myIndex == (oIndex + 2) % 3 -> 0
            else -> 6
        }
    }

    fun part1(): Int = readLines("day2.txt").map(::score).sum()
    fun part2(): Int = readLines("day2.txt").map(::score2).sum()
}