package com.sideproject.data.album.remote.api

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder().url(originalRequest.url()).build()
        Log.d("RequestInterceptor", request.toString())
        return chain.proceed(request)
    }
}
