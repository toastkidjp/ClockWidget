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
     * Convert milliseconds offset value to offset string form.
     *
     * @param offset Milliseconds
     */
    operator fun invoke(offset: Long): String {
        return String.format("+%02d:00", (offset / TimeUnit.HOURS.toMillis(1)))
    }
}