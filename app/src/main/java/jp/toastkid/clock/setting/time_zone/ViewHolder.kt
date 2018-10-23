/*
 * Copyright (c) 2018 toastkidjp.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html.
 */
package jp.toastkid.clock.setting.time_zone

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import jp.toastkid.clock.R
import jp.toastkid.clock.libs.OffsetConverter

/**
 * @author toastkidjp
 */
class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun setTitle(title: String) {
        view.findViewById<TextView>(R.id.title).setText(title)
    }

    fun setOffset(offset: Long) {
        view.findViewById<TextView>(R.id.offset).setText(OffsetConverter(offset))
    }

    fun setOnClick(listener: View.OnClickListener) {
        view.setOnClickListener(listener)
    }
}