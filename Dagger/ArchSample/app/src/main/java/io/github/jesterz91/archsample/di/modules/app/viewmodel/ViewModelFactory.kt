package io.github.jesterz91.archsample.di.modules.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(
    private val creators: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>> // ViewModel 클래스를 key로 갖는 멀티 바인딩된 Map
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        // ViewModel 클래스를 키로하여, ViewModel 객체를 생성하는 Provider를 가져온다.
        val found: Map.Entry<Class<out ViewModel>, Provider<ViewModel>>? = creators.entries.find { modelClass.isAssignableFrom(it.key) }

        val creator: Provider<ViewModel> = found?.value ?: throw IllegalArgumentException("unknown model class $modelClass")

        try {
            @Suppress("UNCHECKED_CAST")
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}