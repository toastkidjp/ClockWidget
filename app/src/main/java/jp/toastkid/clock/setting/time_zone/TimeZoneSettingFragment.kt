/*
 * Copyright (c) 2018 toastkidjp.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html.
 */
package jp.toastkid.clock.setting.time_zone

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.toastkid.clock.R
import jp.toastkid.clock.appwidget.RemoteViewsFactory
import jp.toastkid.clock.appwidget.SingleWidgetProvider
import jp.toastkid.clock.libs.OffsetConverter
import jp.toastkid.clock.setting.PreferenceApplier
import kotlinx.android.synthetic.main.fragment_time_zone.*
import kotlinx.android.synthetic.main.item_time_zone.*
import java.util.*

/**
 * @author toastkidjp
 */
class TimeZoneSettingFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_time_zone, container, false)
    }

    private lateinit var adapter: Adapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activityContext = context ?: return

        val preferences = PreferenceApplier(activityContext)
        adapter = Adapter(preferences) {
            setTimeZoneText(preferences.currentTimeZoneId())
            SingleWidgetProvider.updateWidget(activityContext, RemoteViewsFactory(activityContext))
        }

        time_zones.adapter = adapter
        time_zones.layoutManager =
                LinearLayoutManager(activityContext, LinearLayoutManager.VERTICAL, false)

        filter.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

            override fun afterTextChanged(s: Editable?) = Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.select(s.toString())
                adapter.notifyDataSetChanged()
            }
        })

        setTimeZoneText(preferences.currentTimeZoneId())

        adapter.select()
    }

    private fun setTimeZoneText(currentTimeZone: String) {
        val timeZone = if (TextUtils.isEmpty(currentTimeZone)) {
            TimeZone.getDefault()
        } else {
            TimeZone.getTimeZone(currentTimeZone)
        }
        title.setText(timeZone.id)
        offset.setText(OffsetConverter(timeZone.rawOffset.toLong()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
}