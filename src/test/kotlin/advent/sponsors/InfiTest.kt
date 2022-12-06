package advent.sponsors

import org.junit.jupiter.api.Test
import readLines
import kotlin.test.assertEquals

class InfiTest {
    @Test
    fun `Should turn right when already North West`() {
        assertEquals(Direction.NORTH, Direction.NORTH_WEST.turn(45))
    }

    @Test
    fun `Should turn left when facing North`() {
        assertEquals(Direction.NORTH_WEST, Direction.NORTH.turn(-45))
    }

    @Test
    fun `Should find first start of packet marker for sample`() {
        assertEquals(12, Infi.part1(readLines("infi_sample.txt")))
    }

    @Test
    fun `Should find first start of packet marker for input`() {
        assertEquals(35, Infi.part1(readLines("infi.txt")))
    }
}