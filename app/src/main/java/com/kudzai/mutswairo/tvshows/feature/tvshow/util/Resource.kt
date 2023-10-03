package com.kudzai.mutswairo.tvshows.feature.tvshow.util

sealed class Resource<T>(data: T? = null, message: String? = null) {
    class Loading<T> : Resource<T>()
    class Error<T>(val data: T? = null, val message: String) : Resource<T>()
    class Success<T>(val data: T, val message: String? = null) : Resource<T>()
}
