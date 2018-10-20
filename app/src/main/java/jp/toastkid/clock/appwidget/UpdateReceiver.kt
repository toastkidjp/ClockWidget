/*
 * Copyright (c) 2018 toastkidjp.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html.
 */
package jp.toastkid.clock.appwidget

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.POWER_SERVICE
import android.content.Intent
import android.os.PowerManager

/**
 * @author toastkidjp
 */
class UpdateReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null) {
            return
        }
        if ((context.getSystemService(POWER_SERVICE) as? PowerManager)?.isScreenOn == true) {
            SingleWidgetProvider.updateWidget(context, RemoteViewsFactory(context))
        }
    }

    companion object {
        fun makePendingIntent(context: Context): PendingIntent {
            return PendingIntent.getBroadcast(
                    context,
                    1,
                    Intent(context, UpdateReceiver::class.java),
                    PendingIntent.FLAG_UPDATE_CURRENT
            )
        }
    }
}