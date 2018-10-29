/*
 * Copyright (c) 2018 toastkidjp.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html.
 */
package jp.toastkid.clock.license

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import jp.toastkid.clock.R

/**
 * @author toastkidjp
 */
class Adapter(
        private val licenseAssets: Array<String>,
        private val onClick: (String) -> Unit
        ) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(
                    LayoutInflater.from(parent.context)
                            .inflate(R.layout.item_license_information, parent, false)
            )

    override fun getItemCount() = licenseAssets.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = licenseAssets[position]
        holder.itemView.findViewById<TextView>(R.id.title_license).text = ExtensionRemover(item)
        holder.itemView.setOnClickListener { onClick(item) }
    }
}