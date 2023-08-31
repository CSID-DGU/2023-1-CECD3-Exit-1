package com.cecd.exitmed.data.interceptor

import com.cecd.exitmed.data.dataSource.local.ExitLocalDataSource
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val dataSource: ExitLocalDataSource
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val authRequest = if (!dataSource.isLogin) originalRequest
        else originalRequest.newBuilder().addHeader("Authorization", dataSource.accessToken).build()
        val response = chain.proceed(authRequest)

        return response
    }
}
