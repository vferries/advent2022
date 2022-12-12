package advent

import java.math.BigInteger
import java.util.function.UnaryOperator

object Day11 {
    fun part1(lines: List<String>): BigInteger = computeMonkeyBusinessLevel(lines, 20) { it / 3.toBigInteger() }

    fun part2(lines: List<String>): BigInteger = computeMonkeyBusinessLevel(lines, 10_000)

    private fun computeMonkeyBusinessLevel(
        lines: List<String>,
        rounds: Int,
        worryLevel: UnaryOperator<BigInteger> = UnaryOperator.identity()
    ): BigInteger {
        val monkeys = parseMonkeysMap(lines)
        val inspected = monkeys.keys.map { BigInteger.ZERO }.toMutableList()
        val lcmWorryLevels = monkeys.values.map { it.divisibleBy }.reduce(BigInteger::multiply)
        repeat(rounds) {
            for ((k, monkey) in monkeys.entries) {
                for (item in monkey.items) {
                    inspected[k] += BigInteger.ONE
                    val new = monkey.operation.apply(item)
                    val worry = worryLevel.apply(new) % lcmWorryLevels
                    val destination =
                        if (worry % monkey.divisibleBy == BigInteger.ZERO) monkey.ifTrue else monkey.ifFalse
                    monkeys.getValue(destination).items.add(worry)
                }
                monkey.items.clear()
            }
        }
        return inspected
            .sorted()
            .takeLast(2)
            .reduce(BigInteger::times)
    }

    private fun parseMonkeysMap(lines: List<String>): Map<Int, Monkey> {
        val monkeys = lines.chunked(7)
            .mapIndexed { i, monkeyDescr ->
                val items = monkeyDescr[1]
                    .split(": ")[1]
                    .split(", ")
                    .map(String::toBigInteger)
                    .toMutableList()
                val operation = parseOperation(monkeyDescr[2])
                val divider = monkeyDescr[3].split(" ").last().toBigInteger()
                val ifTrue = monkeyDescr[4].split(" ").last().toInt()
                val ifFalse = monkeyDescr[5].split(" ").last().toInt()
                i to Monkey(
                    items,
                    operation,
                    divider,
                    ifTrue,
                    ifFalse
                )
            }
            .toMap()
        return monkeys
    }

    private fun parseOperation(monkeyDescr: String): (BigInteger) -> BigInteger {
        val (_, op, r) = monkeyDescr
            .split("= ")[1]
            .split(" ")
        val operation = { old: BigInteger ->
            val right = when (r) {
                "old" -> old
                else -> r.toBigInteger()
            }
            when (op) {
                "+" -> old + right
                "*" -> old * right
                else -> throw IllegalArgumentException("Unrecognized operator $op")
            }
        }
        return operation
    }

    data class Monkey(
        val items: MutableList<BigInteger>,
        val operation: UnaryOperator<BigInteger>,
        val divisibleBy: BigInteger,
        val ifTrue: Int,
        val ifFalse: Int
    )
}