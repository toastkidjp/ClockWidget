package jp.toastkid.clock.libs

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

/**
 * [OffsetConverter]'s test cases.
 *
 * @author toastkidjp
 */
class OffsetConverterTest {

    /**
     * Check normal offset case.
     */
    @Test
    fun checkNormalCase() {
        val tokyo = TimeZone.getTimeZone("Asia/Tokyo").rawOffset.toLong()
        assertEquals("+09:00", OffsetConverter(tokyo))
    }

    /**
     * Check minus offset case.
     */
    @Test
    fun checkMinusCase() {
        val caracas = TimeZone.getTimeZone("America/Caracas").rawOffset.toLong()
        assertEquals("-04:00", OffsetConverter(caracas))
    }

    /**
     * Check containing fraction cases.
     */
    @Test
    fun checkFractionCase() {
        val pyongyang = TimeZone.getTimeZone("Asia/Pyongyang").rawOffset.toLong()
        assertEquals("+08:30", OffsetConverter(pyongyang))

        val eucla = TimeZone.getTimeZone("Australia/Eucla").rawOffset.toLong()
        assertEquals("+08:45", OffsetConverter(eucla))
    }
}