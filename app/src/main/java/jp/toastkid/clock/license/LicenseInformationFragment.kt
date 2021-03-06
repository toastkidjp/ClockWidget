/*
 * Copyright (c) 2018 toastkidjp.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html.
 */
package jp.toastkid.clock.license

import android.content.res.AssetManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import jp.toastkid.clock.R
import jp.toastkid.clock.libs.TitleProvider
import kotlinx.android.synthetic.main.fragment_license_information.*
import okio.Okio

/**
 * @author toastkidjp
 */
class LicenseInformationFragment : Fragment(), TitleProvider {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_license_information, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activityContext = context ?: return

        val assetManager = activityContext.assets
        val licenseDir = assetManager.list(DIRECTORY)

        menu_licenses.adapter = Adapter(licenseDir) {
            AlertDialog.Builder(activityContext)
                    .setTitle(ExtensionRemover(it))
                    .setMessage(readContent(assetManager, it))
                    .setPositiveButton("Close") { d, _ -> d.dismiss() }
                    .show()
        }
        menu_licenses.layoutManager = LinearLayoutManager(activityContext)
    }

    private fun readContent(assetManager: AssetManager, fileName: String) =
            Okio.buffer(Okio.source(assetManager.open("$DIRECTORY/$fileName")))
                    .use { it.readUtf8() }

    override fun title() = "License Information"

    companion object {
        private const val DIRECTORY = "licenses"
    }
}