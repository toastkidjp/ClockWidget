/*
 * Copyright (c) 2018 toastkidjp.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html.
 */
package jp.toastkid.clock.appwidget

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.POWER_SERVICE
import android.content.Intent
import android.os.PowerManager

/**
 * This receiver is implemented for updating App-Widget.
 *
 * @author toastkidjp
 */
class UpdateReceiver : BroadcastReceiver() {

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null) {
            return
        }

        if (isScreenOn(context)) {
            SingleWidgetProvider.updateWidget(context, RemoteViewsFactory(context))
        }
    }

    /**
     * If screen is off, suppress updating for saving battery.
     *
     * @param context [Context]
     */
    @Suppress("DEPRECATION")
    private fun isScreenOn(context: Context) =
            (context.getSystemService(POWER_SERVICE) as? PowerManager)?.isScreenOn == true

    companion object {

        /**
         * Make [PendingIntent] for updating app-widget.
         *
         * @param context [Context]
         */
        fun makePendingIntent(context: Context): PendingIntent = PendingIntent.getBroadcast(
                context,
                1,
                makeIntent(context),
                PendingIntent.FLAG_UPDATE_CURRENT
        )

        /**
         * Make [Intent] for broadcasting.
         *
         * @param context [Context]
         */
        fun makeIntent(context: Context) = Intent(context, UpdateReceiver::class.java)
    }
}