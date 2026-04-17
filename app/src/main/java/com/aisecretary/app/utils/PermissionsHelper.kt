package com.aisecretary.app.utils

import android.Manifest
import android.app.Activity
import androidx.core.app.ActivityCompat

object PermissionsHelper {
    private val perms = arrayOf(
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.READ_CALL_LOG,
        Manifest.permission.READ_CALENDAR,
        Manifest.permission.WRITE_CALENDAR,
        Manifest.permission.POST_NOTIFICATIONS
    )
    fun requestAll(activity: Activity) {
        ActivityCompat.requestPermissions(activity, perms, 1001)
    }
}
