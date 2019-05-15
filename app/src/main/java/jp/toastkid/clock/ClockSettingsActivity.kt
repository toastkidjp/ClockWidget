package jp.toastkid.clock

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.net.toUri
import androidx.viewpager.widget.ViewPager
import com.google.android.material.snackbar.Snackbar
import jp.toastkid.clock.appwidget.placement.AppWidgetPlacer
import jp.toastkid.clock.libs.PrivacyPolicyLauncher
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_setting_top.*

class ClockSettingsActivity : AppCompatActivity() {

    /**
     * Contents pager's adapter.
     */
    private var pagerAdapter: SettingPagerAdapter? = null

    private lateinit var appWidgetPlacer: AppWidgetPlacer

    /**
     * AD view.
     */
    private lateinit var adView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_setting_top)

        pagerAdapter = SettingPagerAdapter(supportFragmentManager)
        initViewPager()

        appWidgetPlacer = AppWidgetPlacer(this)

        initToolbar()
        initAd()
    }

    private fun initViewPager() {
        container?.also {
            it.adapter = pagerAdapter
            it.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) = Unit

                override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int
                ) = Unit

                override fun onPageSelected(position: Int) {
                    toolbar.title = pagerAdapter?.getPageTitle(position)
                }
            })
        }
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        toolbar.run {
            setNavigationIcon(R.drawable.ic_close)
            setNavigationOnClickListener { finish() }
            setTitleTextColor(Color.WHITE)
        }
        toolbar.overflowIcon?.let { DrawableCompat.setTint(it, Color.WHITE) }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        if (appWidgetPlacer.isTargetOs()) {
            menuInflater.inflate(R.menu.placement, menu)
        }
        menu.findItem(R.id.menu_version)?.title = "App Version: ${BuildConfig.VERSION_NAME}"
        return true
    }

    /**
     * Initialize banner AD.
     */
    private fun initAd() {
        MobileAds.initialize(this, getString(R.string.ad_app_id))
        initAdView()
        loadAd()
    }

    /**
     * Initialize AdView.
     */
    private fun initAdView() {
        adView = AdView(this)
        adView.adSize = AdSize.BANNER
        adView.adUnitId = getString(R.string.ad_unit_id)
        ad_container.addView(adView)
    }

    /**
     * Load AD.
     */
    private fun loadAd() {
        adView.loadAd(
                AdRequest.Builder()
                        .addTestDevice("B4F1033D07067316E4ED247D9F18E7D7")
                        .build()
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        adView.destroy()
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
                R.id.menu_placement -> {
                    if (appWidgetPlacer.isTargetOs()) {
                        appWidgetPlacer()
                    }
                    true
                }
                R.id.menu_date_time -> {
                    startActivity(Intent().also { it.action = Settings.ACTION_DATE_SETTINGS })
                    true
                }
                R.id.privacy_policy -> {
                    PrivacyPolicyLauncher(this)
                    true
                }
                R.id.menu_licenses -> {
                    container?.currentItem = 2
                    true
                }
                R.id.menu_version -> {
                    startActivity(
                            Intent(Intent.ACTION_VIEW).also {
                                it.data = "market://details?id=jp.toastkid.clock".toUri()
                            }
                    )
                    true
                }
                R.id.menu_exit -> {
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
