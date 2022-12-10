package advent.sponsors

import java.lang.IllegalStateException

object ReaktorSvalbard {
    /*
    initializing system
... init var = G H N R T
... init const = A I U E O
... init rules
... G => GN
... R => NT
... N => RH
... H => TG
... T => HH
complete
input seed: ************
seed accepted
starting up...
eval seed on generation 0
FATAL ERROR
debug head on generation 6
printing output...
HHGNHHGNGNRHNTTGHHGNHHGNGNRHNTTGGNRHNTTGRHHHHHGNNTTGTGTGTGTGGNRHUNTTGTGTGTGTGGNRHTGTGGNRHTGTGGNRHTGTGGNRHTGTGGNRHGNRHNTTGRHHHHHGNTGTGGNRHTGTGGNRHGNRHNTTGRHHHHHGNTGTGGNRHTGTGGNRHGNRHNTTGRHHHHHGNORHHHHHGNHHGNHHGNHHGNHHGNGNRHNTTGHHGNHHGNGNRHNTTGHHGNHHGNGNRHNTTGGNRHNTTGRHHHHHGNNTTGTGTGTGTGGNRHRHHHHHGNHHGNHHGNHHGNHHGNGNRHNTTGATGTGGNRHTGTGGNRHGNRHNTTGRHHHHHGNTGTGGNRHTGTGGNRHGNRHNTTGRHHHHHGNHHGNHHGNGNRHNTTGHHGNHHGNGNRHNTTGGNRHNTTGRHHHHHGNNTTGTGTGTGTGGNRHERHHHHHGNHHGNHHGNHHGNHHGNGNRHNTTGHHGNHHGNGNRHNTTGHHGNHHGNGNRHNTTG

Gen 3 :
Gen 4 : 129
Gen 5 : 258
Gen 6 : 516
     */
    val rules = mapOf(
        "GN" to "G",
        "NT" to "R",
        "RH" to "N",
        "TG" to "H",
        "HH" to "T"
    )

    @JvmStatic
    fun main(args: Array<String>) {
        println(tunnel())
        println(mainchamber())
        println(vault())
    }

    private fun vault(): String {
        val input = "TACGATGCATGGCTACYZZWXVAVYZTTAGACTAGCACTCGA"
        val matchings = mapOf(
            'V' to "55",
            'W' to "4E",
            'X' to "46",
            'Y' to "52",
            'Z' to "45"
        ).mapValues { (_, v) -> Char(v.toInt(16)) }.toMutableMap()
        matchings['A'] = 'T'
        matchings['T'] = 'A'
        matchings['C'] = 'G'
        matchings['G'] = 'C'
        return input
            .map { matchings.getOrElse(it) { it } }
            .joinToString("")
            .drop(15)
            .dropLast(16)
    }

    private fun tunnel(): String = "ROOTSOFLIFE" // Extracted from JavaScript for part 1

    private fun mainchamber(): String {
        var gen =
            "HHGNHHGNGNRHNTTGHHGNHHGNGNRHNTTGGNRHNTTGRHHHHHGNNTTGTGTGTGTGGNRHUNTTGTGTGTGTGGNRHTGTGGNRHTGTGGNRHTGTGGNRHTGTGGNRHGNRHNTTGRHHHHHGNTGTGGNRHTGTGGNRHGNRHNTTGRHHHHHGNTGTGGNRHTGTGGNRHGNRHNTTGRHHHHHGNORHHHHHGNHHGNHHGNHHGNHHGNGNRHNTTGHHGNHHGNGNRHNTTGHHGNHHGNGNRHNTTGGNRHNTTGRHHHHHGNNTTGTGTGTGTGGNRHRHHHHHGNHHGNHHGNHHGNHHGNGNRHNTTGATGTGGNRHTGTGGNRHGNRHNTTGRHHHHHGNTGTGGNRHTGTGGNRHGNRHNTTGRHHHHHGNHHGNHHGNGNRHNTTGHHGNHHGNGNRHNTTGGNRHNTTGRHHHHHGNNTTGTGTGTGTGGNRHERHHHHHGNHHGNHHGNHHGNHHGNGNRHNTTGHHGNHHGNGNRHNTTGHHGNHHGNGNRHNTTG"
        repeat(6) {
            var prevGen = ""
            var i = 0
            while (i <= gen.lastIndex) {
                when (gen[i]) {
                    'A', 'E', 'I', 'O', 'U' -> {
                        prevGen += gen[i]
                    }

                    else -> {
                        val key = "" + gen[i] + gen[i + 1]
                        if (rules.containsKey(key)) {
                            prevGen += rules[key]
                        } else {
                            throw IllegalStateException("Unknown rule $key at index $i")
                        }
                        i++
                    }
                }
                i++
            }
            gen = prevGen
        }
        return gen
    }
}