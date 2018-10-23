/*
 * Copyright (c) 2018 toastkidjp.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html.
 */
package jp.toastkid.clock.setting

import android.content.Context
import android.text.TextUtils
import java.util.*

/**
 * @author toastkidjp
 */
class PreferenceApplier(context: Context) {

    private val preferences =
            context.getSharedPreferences(javaClass.canonicalName, Context.MODE_PRIVATE)

    enum class Key {
        TIME_ZONE
    }

    fun currentTimeZone(): TimeZone = preferences.getString(Key.TIME_ZONE.name, "").let {
        if (TextUtils.isEmpty(it)) TimeZone.getDefault()
        else TimeZone.getTimeZone(it)
    }

    fun currentTimeZoneId(): String {
        return preferences.getString(Key.TIME_ZONE.name, "")
    }

    fun setTimeZone(timeZone: String) {
        preferences.edit().putString(Key.TIME_ZONE.name, timeZone).apply()
    }
}