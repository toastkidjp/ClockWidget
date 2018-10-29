package jp.toastkid.clock

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
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


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                R.id.menu_color -> {
                    container?.currentItem = 1
                    true
                }
                R.id.menu_time_zone -> {
                    container?.currentItem = 0
                    true
                }
                R.id.exit -> {
                    moveTaskToBack(true)
                    true
                }
                else -> {
                    Snackbar.make(
                            findViewById(android.R.id.content),
                            R.string.message_implementing,
                            Snackbar.LENGTH_SHORT
                    ).show()
                    super.onOptionsItemSelected(item)
                }
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
