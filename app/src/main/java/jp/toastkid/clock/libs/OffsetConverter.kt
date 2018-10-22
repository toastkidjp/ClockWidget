/*
 * Copyright (c) 2018 toastkidjp.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html.
 */
package jp.toastkid.clock.libs

import java.util.concurrent.TimeUnit

/**
 * Converter of time-zone offset.
 *
 * @author toastkidjp
 */
object OffsetConverter {

    /**
     * One hour milliseconds.
     */
    private val oneHour = TimeUnit.HOURS.toMillis(1)

    /**
     * One minute milliseconds.
     */
    private val oneMinute = TimeUnit.MINUTES.toMillis(1)

    /**
     * Convert milliseconds offset value to offset string form.
     * For example, +09:00
     *
     * @param offset Milliseconds
     */
    operator fun invoke(offset: Long): String {
        val hour = offset / oneHour
        val minutes = (offset % oneHour) / oneMinute
        return (if (offset >= 0) "+" else "-") + String.format("%02d:%02d", Math.abs(hour), minutes.toInt())
    }
}