package jp.toastkid.clock

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import jp.toastkid.clock.license.LicenseInformationFragment
import jp.toastkid.clock.setting.color.ColorSettingFragment
import jp.toastkid.clock.setting.time_zone.TimeZoneSettingFragment

class SettingPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val fragments: List<Fragment> = listOf(
            TimeZoneSettingFragment(),
            ColorSettingFragment(),
            LicenseInformationFragment()
    )

    override fun getCount(): Int = fragments.size

    override fun getItem(position: Int): Fragment = fragments.get(position)

}