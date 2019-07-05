/*
 * Copyright (c) 2018 toastkidjp.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html.
 */
package jp.toastkid.clock.appwidget

import android.app.AlarmManager
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.widget.RemoteViews
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * @author toastkidjp
 */
class SingleWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetIds: IntArray
    ) {
        val alarmManager: AlarmManager =
                context.getSystemService(ALARM_SERVICE) as? AlarmManager ?: return
        alarmManager.setRepeating(
                AlarmManager.RTC,
                nextInvocationTime(),
                oneMinute,
                UpdateReceiver.makePendingIntent(context)
        )
        updateWidget(context, RemoteViewsFactory(context))
    }

    private fun nextInvocationTime(): Long {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MINUTE, 1)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.timeInMillis
    }

    companion object {

        private val oneMinute = TimeUnit.MINUTES.toMillis(1)

        /**
         * Update widget.
         *
         * @param context
         * @param remoteViews
         */
        fun updateWidget(context: Context, remoteViews: RemoteViews) {
            val componentName = ComponentName(context, SingleWidgetProvider::class.java)
            val manager = AppWidgetManager.getInstance(context)
            manager.updateAppWidget(componentName, remoteViews)
        }
    }
}