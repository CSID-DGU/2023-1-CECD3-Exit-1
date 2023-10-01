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
        else originalRequest.newBuilder().addHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJleGl0VG9rZW4iLCJleHAiOjE2OTczMDc2NjMsInVzZXJJZCI6IlJpdmFza2kiLCJ1c2VyUGFzc3dvcmQiOiIkMmEkMTAkRDlES1pKMHcxZVJ6YUx6T1ZZYXRDLmpMYlZRM1dZZVdtL1ZhZ3kzaG5YUlE0b1JualVIUDYifQ.txdqPthFms_jRhhPa6OOF_nCx-eYUrVlFj34olJj-OHoCi0Wr1U-hZjWWCIBIvUJTTUok57hgmKWsc_Hwd_upQ").build()
        val response = chain.proceed(authRequest)

        return response
    }
}
