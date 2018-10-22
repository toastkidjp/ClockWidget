package jp.toastkid.clock

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_setting_top.*

class ClockSettingsActivity : AppCompatActivity() {

    /**
     * Contents pager's adapter.
     */
    private var pagerAdapter: SettingPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_setting_top)

        pagerAdapter = SettingPagerAdapter(supportFragmentManager)
        container?.adapter = pagerAdapter
    }

    companion object {

        /**
         * Make this [Activity]'s starting intent.
         *
         * @param context [Context]
         */
        fun makeIntent(context: Context) =
                Intent(context, ClockSettingsActivity::class.java)
                        .also { it.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP }
    }
}
