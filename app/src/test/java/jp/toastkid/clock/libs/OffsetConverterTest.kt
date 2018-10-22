package jp.toastkid.clock.libs

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

/**
 * @author toastkidjp
 */
class OffsetConverterTest {

    @Test
    fun testInvoke() {
        val pyongyang = TimeZone.getTimeZone("Asia/Pyongyang").rawOffset.toLong()
        assertEquals("+08:30", OffsetConverter(pyongyang))
        val tokyo = TimeZone.getTimeZone("Asia/Tokyo").rawOffset.toLong()
        assertEquals("+09:00", OffsetConverter(tokyo))
        val caracas = TimeZone.getTimeZone("America/Caracas").rawOffset.toLong()
        assertEquals("-04:00", OffsetConverter(caracas))
        val eucla = TimeZone.getTimeZone("Australia/Eucla").rawOffset.toLong()
        assertEquals("+08:45", OffsetConverter(eucla))
    }
}