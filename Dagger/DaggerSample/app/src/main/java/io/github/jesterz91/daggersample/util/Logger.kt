package io.github.jesterz91.daggersample.util

import android.util.Log
import io.github.jesterz91.daggersample.BuildConfig

interface Logger {

    val loggerTag: String
        get() {
            val tag = javaClass.simpleName
            return if (tag.length <= 23) tag else tag.substring(0, 23)
        }

    fun verbose(message: Any?) {
        if (BuildConfig.DEBUG) {
            Log.v(loggerTag, "$message")
        }
    }

    fun debug(message: Any?) {
        if (BuildConfig.DEBUG) {
            Log.d(loggerTag, "$message")
        }
    }

    fun info(message: Any?) {
        if (BuildConfig.DEBUG) {
            Log.i(loggerTag, "$message")
        }
    }

    fun warn(message: Any?) {
        if (BuildConfig.DEBUG) {
            Log.w(loggerTag, "$message")
        }
    }

    fun error(message: Any?) {
        if (BuildConfig.DEBUG) {
            Log.e(loggerTag, "$message")
        }
    }

    fun wtf(message: Any?) {
        if (BuildConfig.DEBUG) {
            Log.wtf(loggerTag, "$message")
        }
    }
}