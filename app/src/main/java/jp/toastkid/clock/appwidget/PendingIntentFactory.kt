package jp.toastkid.clock.appwidget

import android.app.PendingIntent
import android.content.Context
import jp.toastkid.clock.ClockSettingsActivity

/**
 * @author toastkidjp
 */
object PendingIntentFactory {

    operator fun invoke(context: Context): PendingIntent = PendingIntent.getActivity(
            context,
            1,
            ClockSettingsActivity.makeIntent(context),
            PendingIntent.FLAG_UPDATE_CURRENT
    )
}