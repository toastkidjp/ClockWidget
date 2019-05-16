package jp.toastkid.clock

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import jp.toastkid.clock.libs.TitleProvider
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

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getPageTitle(position: Int): CharSequence? {
        val fragment = getItem(position)
        return if (fragment is TitleProvider) fragment.title()
                else fragment.getString(R.string.app_name)
    }
}