package jp.toastkid.clock.license

/**
 * TODO implement test
 * @author toastkidjp
 */
object ExtensionRemover {

    operator fun invoke(fileName: String) =
            fileName.substring(0, fileName.lastIndexOf("."))
}