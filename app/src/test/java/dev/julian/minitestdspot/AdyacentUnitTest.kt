package dev.julian.minitestdspot

import dev.julian.minitestdspot.problem.Adjacent
import org.junit.Assert.assertEquals
import org.junit.Test

class AdyacentUnitTest {
    @Test fun countAdyacentTest() {
        assertEquals(Adjacent.countLetterAdjacent("AABAAB"), 2)
        assertEquals(Adjacent.countLetterAdjacent("AABAABBABABABABABABBBABABABABABBABBABA"), 7)
    }
}