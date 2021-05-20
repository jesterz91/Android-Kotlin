package io.github.jesterz91.room.extension

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <reified T> Fragment.listener() = object : ReadOnlyProperty<Fragment, T?> {

    private var value: T? = null

    init {
        lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
            fun onCreate() {
                value = requireActivity() as? T
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroy() {
                value = null
            }
        })
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T? {
        return value
    }
}