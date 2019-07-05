/*
 * Copyright (c) 2018 toastkidjp.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html.
 */
package jp.toastkid.clock.setting.color

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.madrapps.pikolo.listeners.SimpleColorSelectionListener
import jp.toastkid.clock.R
import jp.toastkid.clock.appwidget.UpdateReceiver
import jp.toastkid.clock.libs.TitleProvider
import jp.toastkid.clock.setting.PreferenceApplier
import kotlinx.android.synthetic.main.fragment_color.*

/**
 * @author toastkidjp
 */
class ColorSettingFragment : Fragment(), TitleProvider {

    private var preferenceApplier: PreferenceApplier? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(LAYOUT_ID, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let { preferenceApplier = PreferenceApplier(it) }

        color_picker.setColorSelectionListener(object : SimpleColorSelectionListener() {
            override fun onColorSelected(color: Int) {
                preferenceApplier?.setFontColor(color)
                context?.let {
                    LocalBroadcastManager.getInstance(it)
                            .sendBroadcast(UpdateReceiver.makeIntent(it))
                }
            }
        })
        preferenceApplier?.let { color_picker.setColor(it.fontColor()) }
    }

    override fun title() = "Font color setting"

    companion object {

        @LayoutRes
        private const val LAYOUT_ID = R.layout.fragment_color
    }
}