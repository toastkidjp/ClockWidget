package jp.toastkid.clock.license

/**
 * TODO implement test
 * @author toastkidjp
 */
object ExtensionRemover {

    operator fun invoke(fileName: String): String {
        val endIndex = fileName.lastIndexOf(".")
        if (endIndex == -1) {
            return fileName
        }
        return fileName.substring(0, endIndex)
    }
}