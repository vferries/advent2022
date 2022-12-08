package advent

object Day7 {
    fun part1(lines: List<String>): Int = parseTree(lines)
        .values.filter { it.size() <= 100_000 }
        .sumOf(Folder::size)

    private fun parseTree(lines: List<String>): Map<String, Folder> {
        val root = Folder("", null)
        var currentDir = root
        val folders = mutableMapOf("" to root)
        for (line in lines) {
            val parts = line.split(" ")
            when {
                parts[0] == "$" && parts[1] == "cd" -> {
                    currentDir = when (parts[2]) {
                        "/" -> root
                        ".." -> currentDir.parent ?: root
                        else -> {
                            val path = currentDir.path + "/" + parts[2]
                            folders.getOrPut(path) { Folder(path, currentDir) }
                        }
                    }
                }

                parts[0] == "$" && parts[1] == "ls" -> {}

                else -> {
                    currentDir.content +=
                        if (parts[0] == "dir") {
                            val path = currentDir.path + "/" + parts[1]
                            folders.getOrPut(path) { Folder(path, currentDir) }
                        } else {
                            Single(currentDir.path + "/" + parts[1], currentDir, parts[0].toInt())
                        }
                }
            }
        }
        return folders
    }

    fun part2(lines: List<String>): Int {
        val folders = parseTree(lines)
        val totalUsed = folders.getValue("").size()
        val remaining = 70_000_000 - totalUsed
        return folders.values.map(Folder::size).filter { remaining + it >= 30_000_000 }.min()
    }
}

sealed class File(open val path: String, open val parent: Folder?) {
    abstract fun size(): Int
}

data class Single(override val path: String, override val parent: Folder?, val size: Int) : File(path, parent) {
    override fun size() = size

    override fun toString(): String = "Single($path, $size)"
}

data class Folder(override val path: String, override val parent: Folder?, var content: List<File> = listOf()) :
    File(path, parent) {
    override fun size(): Int = content.sumOf(File::size)
    override fun toString(): String = "Folder($path, $content)"
}