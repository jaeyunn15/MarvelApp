package com.project.marvelapp

import android.content.SharedPreferences
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.callbackFlow

fun SharedPreferences.getSharedPreferenceFlow(sharedKey: String) = callbackFlow {
    val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        if (key == sharedKey) {
            trySend(key)
        }
    }
    registerOnSharedPreferenceChangeListener(listener)
    if (contains(sharedKey)) {
        send(null)
    }
    awaitClose { unregisterOnSharedPreferenceChangeListener(listener) }
}.buffer(Channel.UNLIMITED)