package jp.toastkid.clock

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import jp.toastkid.clock.setting.time_zone.TimeZoneSettingFragment

class ClockSettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_setting_top)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, TimeZoneSettingFragment())
        transaction.commit()
    }

    companion object {

        fun makeIntent(context: Context): Intent {
            val intent = Intent(context, ClockSettingsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            return intent
        }
    }
}
