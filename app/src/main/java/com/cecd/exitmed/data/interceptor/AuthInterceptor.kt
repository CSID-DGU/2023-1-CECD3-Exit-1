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
        else originalRequest.newBuilder().addHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJleGl0VG9rZW4iLCJleHAiOjE3MDI2MjE3NjEsInVzZXJJZCI6ImV4aXQxQG5hdmVyLmNvbSIsInVzZXJQYXNzd29yZCI6IiQyYSQxMCRGenZHNWM3UWluNkhJZVNBbG13dlFldlN2RXJPSHhWdWpBSTRZRWI5N241LkEwdGtmR09mZSJ9.mfKSEnM_WGUdtqN20qIfv6BSHrqN3t9c_oi8T3LHzrRVOBnoYlVydsH9mgzqWT4zrsJcZ3zRU_ax5T6BYQV2Xw").build()
        val response = chain.proceed(authRequest)

        return response
    }
}
