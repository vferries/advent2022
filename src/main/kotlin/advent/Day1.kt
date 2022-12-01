package advent

object Day1 {
    fun maximumCalories(text: String): Int = caloriesByElf(text).max()

    fun top3Calories(text: String): Int = caloriesByElf(text).sortedDescending().take(3).sum()

    private fun caloriesByElf(text: String) = text
        .split("\n\n")
        .map { elfe -> elfe.split("\n").map(String::toInt).sum() }
}