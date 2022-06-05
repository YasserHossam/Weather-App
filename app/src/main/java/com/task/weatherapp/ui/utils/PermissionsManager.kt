package com.task.weatherapp.ui.utils

import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.UiContext
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import kotlinx.coroutines.CompletableDeferred

abstract class PermissionsManager(@UiContext private val context: Context) {

    abstract fun permissions(): Array<String>

    suspend fun requestPermissions(): Boolean {
        val response = CompletableDeferred<Boolean>()
        Permissions.check(context, permissions(), null, null, object : PermissionHandler() {
            @SuppressLint("MissingPermission")
            override fun onGranted() {
                response.complete(true)
            }

            override fun onDenied(context: Context?, deniedPermissions: ArrayList<String>?) {
                super.onDenied(context, deniedPermissions)
                response.complete(false)
            }

            override fun onBlocked(context: Context?, blockedList: ArrayList<String>?): Boolean {
                val result = super.onBlocked(context, blockedList)
                response.complete(false)
                return result
            }

            override fun onJustBlocked(
                context: Context?,
                justBlockedList: ArrayList<String>?,
                deniedPermissions: ArrayList<String>?
            ) {
                super.onJustBlocked(context, justBlockedList, deniedPermissions)
                response.complete(false)
            }
        })
        return response.await()
    }
}