package com.sideproject.leboncoinapp.core.shareprefs

import android.content.SharedPreferences
import javax.inject.Inject

class LeboncoinSharedPrefsImpl @Inject constructor(
    private val sharedPrefs: SharedPreferences,
) : LeboncoinSharedPrefs {

    companion object {
        private const val KEY_STATE_APP = "state_app"
    }

    override fun getState(): Int {
        return sharedPrefs.getInt(KEY_STATE_APP, 0)
    }
}
