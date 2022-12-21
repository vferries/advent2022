package advent

object Day21 {
    fun part1(lines: List<String>): Long {
        val monkeys = lines.map { it.split(": ") }
            .filter { (_, right) -> right[0].isDigit() }
            .associate { (name, number) -> name to number.toLong() }.toMutableMap()
        var waitingMonkeys = lines.map { it.split(": ") }
            .filter { (_, right) -> !right[0].isDigit() }
            .associate { (name, operation) ->
                val (left, operator, right) = operation.split(" ")
                val op: (Long, Long) -> Long = when (operator) {
                    "+" -> { a, b -> a + b }
                    "-" -> { a, b -> a - b }
                    "*" -> { a, b -> a * b }
                    "/" -> { a, b -> a / b }
                    else -> throw UnsupportedOperationException("Unknown operator $operator")
                }
                name to Triple(left, right, op)
            }.toMutableMap()
        while (!monkeys.containsKey("root")) {
            val readyMonkeys = waitingMonkeys.filter { (_, triple) ->
                val (left, right, _) = triple
                monkeys.containsKey(left) && monkeys.containsKey(right)
            }
            monkeys.putAll(readyMonkeys.map { (name, triple) ->
                val (left, right, op) = triple
                name to op.invoke(monkeys.getValue(left), monkeys.getValue(right))
            })
            for (key in readyMonkeys.keys) {
                waitingMonkeys.remove(key)
            }
        }
        return monkeys.getValue("root")
    }

    fun part2(lines: List<String>): Long {
        val monkeys = lines.map { it.split(": ") }
            .associate { (name, operation) -> name to operation }
        val (left, _, right) = monkeys.getValue("root").split(" ")
        var human = compute(left, monkeys)
        var other = compute(right, monkeys).toLong()
        while (true) {
           human = human.drop(1).dropLast(1)
            if (human == "humn") {
                return other
            }
           if (human[0] == '(') {
               val rest = human.takeLastWhile { it != ')' }
               val (_, op, right) = rest.split(" ")
               when (op) {
                   "+" -> other -= right.toLong()
                   "-" -> other += right.toLong()
                   "*" -> other /= right.toLong()
                   "/" -> other *= right.toLong()
                   else -> throw UnsupportedOperationException("Unknown operator $op")
               }
               human = human.dropLastWhile { it != ')' }
           } else {
               val rest = human.takeWhile { it != '(' }
               val (left, op) = rest.split(" ")
               when (op) {
                   "+" -> other -= left.toLong()
                   "-" -> other = left.toLong() - other
                   "*" -> other /= left.toLong()
                   "/" -> other = left.toLong() / other
                   else -> throw UnsupportedOperationException("Unknown operator $op")
               }
               human = human.dropWhile { it != '(' }
           }
        }
    }

    private fun compute(monkey: String, monkeys: Map<String, String>): String {
        if (monkey == "humn") {
            return "(humn)"
        }
        val current = monkeys.getValue(monkey)
        if (current[0].isDigit()) {
            return current
        }
        val (left, op, right) = current.split(" ")
        val cLeft = compute(left, monkeys)
        val cRight = compute(right, monkeys)
        if (cLeft[0].isDigit() && cRight[0].isDigit()) {
            return "" + when (op) {
                "+" -> cLeft.toLong() + cRight.toLong()
                "-" -> cLeft.toLong() - cRight.toLong()
                "*" -> cLeft.toLong() * cRight.toLong()
                "/" -> cLeft.toLong() / cRight.toLong()
                else -> throw UnsupportedOperationException("Unknown operator $op")
            }
        }
        return "(${cLeft} $op ${cRight})"
    }
}