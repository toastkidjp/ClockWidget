package jp.toastkid.clock.appwidget.placement

import android.annotation.TargetApi
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.os.Build
import android.os.Bundle
import jp.toastkid.clock.appwidget.SingleWidgetProvider

/**
 * App widget placement utility.
 *
 * @param context [Context]
 * @author toastkidjp
 */
class AppWidgetPlacer(private val context: Context) {

    /**
     * App widget manager.
     */
    private val appWidgetManager = AppWidgetManager.getInstance(context)

    /**
     * App widget's provider name.
     */
    private val targetAppWidgetProviderName = SingleWidgetProvider::class.java.canonicalName

    /**
     * Place app widget when it's called on Android O and over.
     */
    @TargetApi(Build.VERSION_CODES.O)
    operator fun invoke() {
        appWidgetManager.requestPinAppWidget(
                ComponentName.createRelative(context, targetAppWidgetProviderName),
                Bundle(),
                null
        )
    }

    /**
     * Use for checking running device is target OS.
     */
    fun isTargetOs() =
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
                && appWidgetManager.isRequestPinAppWidgetSupported
}