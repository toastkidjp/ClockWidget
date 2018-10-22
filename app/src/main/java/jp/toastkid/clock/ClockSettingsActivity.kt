package jp.toastkid.clock

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import jp.toastkid.clock.setting.color.ColorSettingFragment

class ClockSettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_setting_top)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, ColorSettingFragment())
        transaction.commit()
    }

    companion object {

        /**
         * Make this acitivity's starting intent.
         *
         * @param context [Context]
         */
        fun makeIntent(context: Context) =
                Intent(context, ClockSettingsActivity::class.java)
                        .also { it.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP }
    }
}
