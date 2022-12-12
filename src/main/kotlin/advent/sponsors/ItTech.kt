package advent.sponsors

object ItTech {
    //TODO Decode that
    val input = "1100011111000 10111101011110 10111110111110 1101111111110 111011111110 1111101110 11111110"

    @JvmStatic
    fun main(args: Array<String>) {
        val result = input.split(" ").map { it.toInt(2) }
        println(result)
    }
}