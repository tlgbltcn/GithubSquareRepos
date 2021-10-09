package com.tlgbltcn.githubsquarerepos.util

import com.tlgbltcn.githubsquarerepos.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain
                .request()
                .newBuilder()
                .header(
                    AUTHORIZATION_HEADER,
                    AUTHORIZATION_VALUE
                )
                .build()
        )
    }

    companion object {
        const val AUTHORIZATION_HEADER = "Authorization"
        const val AUTHORIZATION_VALUE = "Bearer ${BuildConfig.TOKEN}"
    }
}