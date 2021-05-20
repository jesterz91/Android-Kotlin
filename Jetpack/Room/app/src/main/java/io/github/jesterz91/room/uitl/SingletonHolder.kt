package io.github.jesterz91.room.uitl

open class SingletonHolder<out T : Any, in A>(private val creator: (A) -> T) {

    @Volatile
    private var instance: T? = null

    fun getInstance(argument: A): T = instance ?: synchronized(this) {
        instance ?: creator.invoke(argument).also { instance = it }
    }
}