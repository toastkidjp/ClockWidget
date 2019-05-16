/*
 * Copyright (c) 2018 toastkidjp.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html.
 */
package jp.toastkid.clock.setting.time_zone

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import jp.toastkid.clock.R
import jp.toastkid.clock.setting.PreferenceApplier
import java.util.*

/**
 * @author toastkidjp
 */
class Adapter(
        private val preferences: PreferenceApplier,
        private val onClick: () -> Unit
        ) : RecyclerView.Adapter<ViewHolder>() {

    private val master = TimeZone.getAvailableIDs()

    private val timeZoneIds: MutableList<String> = mutableListOf()

    fun select(query: String = "") {
        timeZoneIds.clear()
        timeZoneIds.addAll(master.filter { it.toLowerCase().contains(query) })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_time_zone, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = timeZoneIds.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val timeZone = TimeZone.getTimeZone(timeZoneIds[position])
        holder.setTitle(timeZone.id)
        holder.setOffset(timeZone.rawOffset.toLong())
        holder.setOnClick(View.OnClickListener {
            preferences.setTimeZone(timeZone.id)
            onClick()
            Snackbar.make(it, "Set new TimeZone: ${timeZone.id}", Snackbar.LENGTH_SHORT).show()
        })
    }
}