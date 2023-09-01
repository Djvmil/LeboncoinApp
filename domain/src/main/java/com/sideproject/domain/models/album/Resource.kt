package com.sideproject.domain.models.album

sealed class Resource<out T : Any> {
    object Loading : Resource<Nothing>()
    object Finish : Resource<Nothing>()
    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Error(val throwable: Throwable) : Resource<Nothing>()
}
