package jp.toastkid.clock.libs

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import jp.toastkid.clock.R

/**
 * @author toastkidjp
 */
object PrivacyPolicyLauncher {

    private val URI = Uri.parse("https://tmblr.co/ZDG7Be2NVdctY")

    operator fun invoke(context: Context) = CustomTabsIntent.Builder()
            .setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
            .setStartAnimations(context, android.R.anim.fade_in, android.R.anim.fade_out)
            .setExitAnimations(context, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            .addDefaultShareMenuItem()
            .build()
            .launchUrl(context, URI)
}