package advent

object Day20 {
    fun part1(lines: List<String>): Long {
        val numbers = lines.map(String::toLong)
        val nums = constructNums(numbers)
        mix(nums)
        return sumFirstThreeThousands(numbers, nums)
    }

    fun part2(lines: List<String>): Long {
        val numbers = lines.map(String::toLong).map { it * 811589153 }
        val nums = constructNums(numbers)
        repeat(10) {
            mix(nums)
        }
        return sumFirstThreeThousands(numbers, nums)
    }

    private fun sumFirstThreeThousands(numbers: List<Long>, nums: List<Num>): Long {
        val zeroIndex = numbers.indexOf(0)
        var result = 0L
        var current = nums[zeroIndex]
        repeat(3) {
            repeat(1000) {
                current = current.next
            }
            result += current.n
        }
        return result
    }

    private fun constructNums(numbers: List<Long>): List<Num> {
        val nums = numbers.map { Num(it) }
        for (i in nums.indices) {
            if (i == 0) {
                nums[nums.lastIndex].next = nums[0]
                nums[0].previous = nums[nums.lastIndex]
            } else {
                nums[i - 1].next = nums[i]
                nums[i].previous = nums[i - 1]
                nums[i - 1].initialNext = nums[i]
            }
        }
        return nums
    }

    private fun mix(nums: List<Num>) {
        var current: Num? = nums[0]
        while (current != null) {
            val c = current!!
            var n = (current.n % (nums.size - 1)).toInt()
            if (n > nums.size / 2) {
                n -= nums.size - 1
            }
            if (-n > nums.size / 2) {
                n += nums.size - 1
            }
            var destination = c
            if (n != 0) {
                if (n > 0) {
                    repeat(n) {
                        destination = destination.next
                    }
                } else {
                    repeat(-n + 1) {
                        destination = destination.previous
                    }
                }
                val oldPrev = c.previous
                val oldNext = c.next
                oldPrev.next = oldNext
                oldNext.previous = oldPrev
                val destinationNext = destination.next
                c.previous = destination
                destination.next = c
                destinationNext.previous = c
                c.next = destinationNext
            }
            current = current.initialNext
        }
    }
}

data class Num(val n: Long) {
    var initialNext: Num? = null
    lateinit var previous: Num
    lateinit var next: Num
}
