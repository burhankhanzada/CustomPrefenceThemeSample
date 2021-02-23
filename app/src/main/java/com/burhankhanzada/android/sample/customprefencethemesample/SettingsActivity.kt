package com.burhankhanzada.android.sample.customprefencethemesample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.settings, SettingsFragment())
                    .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
//            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            val screen = preferenceManager.createPreferenceScreen(preferenceManager.context)

            val messagesCategory = PreferenceCategory(context).apply {
                layoutResource = R.layout.sample_preference
                key = "messages_category"
                title = getString(R.string.messages_header)
                screen.addPreference(this)
            }

            EditTextPreference(context).apply {
                layoutResource = R.layout.sample_preference
                key = "signature"
                title = getString(R.string.signature_title)
                summaryProvider = EditTextPreference.SimpleSummaryProvider.getInstance()
                messagesCategory.addPreference(this)
            }

            ListPreference(context).apply {
                layoutResource = R.layout.sample_preference
                key = "reply"
                title = getString(R.string.reply_title)
                entries = resources.getStringArray(R.array.reply_entries)
                entryValues = resources.getStringArray(R.array.reply_values)
                summaryProvider = ListPreference.SimpleSummaryProvider.getInstance()
                messagesCategory.addPreference(this)
            }

            val syncCategory = PreferenceCategory(context).apply {
                layoutResource = R.layout.sample_preference
                key = "sync_category"
                title = getString(R.string.sync_header)
                screen.addPreference(this)
            }


            SwitchPreferenceCompat(context).apply {
                layoutResource = R.layout.sample_preference
                key = "sync"
                title = getString(R.string.sync_title)
                syncCategory.addPreference(this)
            }

            val attachment = SwitchPreferenceCompat(context).apply {
                layoutResource = R.layout.sample_preference
                key = "attachment"
                title = getString(R.string.attachment_title)
                summaryOff = getString(R.string.attachment_summary_off)
                summaryOn = getString(R.string.attachment_summary_on)
                syncCategory.addPreference(this)
            }

            preferenceScreen = screen

            attachment.dependency = "sync"

        }
    }
}