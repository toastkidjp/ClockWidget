package jp.toastkid.clock.license

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * @author toastkidjp
 */
class ExtensionRemoverTest {

    @Test
    fun test() {
        assertEquals("", ExtensionRemover.invoke(""))
        assertEquals("tomato", ExtensionRemover.invoke("tomato"))
        assertEquals("tomato", ExtensionRemover.invoke("tomato."))
        assertEquals("tomato", ExtensionRemover.invoke("tomato.t"))
        assertEquals("tomato", ExtensionRemover.invoke("tomato.txt"))
        assertEquals("tomato.txt", ExtensionRemover.invoke("tomato.txt."))
        assertEquals("tomato.txt", ExtensionRemover.invoke("tomato.txt.o"))
    }

}