/*
 * Copyright (c) 2018 toastkidjp.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html.
 */
package jp.toastkid.clock.appwidget

import android.content.Context
import android.widget.RemoteViews
import jp.toastkid.clock.R
import jp.toastkid.clock.setting.PreferenceApplier
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author toastkidjp
 */
object RemoteViewsFactory {

    private const val APPWIDGET_LAYOUT_ID = R.layout.appwidget_single

    private val DATE_FORMAT = object: ThreadLocal<DateFormat>() {
        override fun initialValue(): DateFormat {
            return SimpleDateFormat("HH:mm", Locale.getDefault())
        }
    }

    operator fun invoke(context: Context): RemoteViews {
        val removeViews = RemoteViews(context.packageName, APPWIDGET_LAYOUT_ID)
        removeViews.setOnClickPendingIntent(R.id.background, PendingIntentFactory(context))
        val currentTimeZone = PreferenceApplier(context).currentTimeZone()
        val dateFormat = DATE_FORMAT.get()
        dateFormat.timeZone = currentTimeZone
        removeViews.setTextViewText(R.id.date_time, dateFormat.format(Date()))
        removeViews.setTextViewText(R.id.timezone, currentTimeZone.id)
        println(Date())
        return removeViews
    }
}