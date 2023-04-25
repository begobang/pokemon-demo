package com.begobang.data.di

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/*
    Retrofit need an OkHttpClient assign, and it also needs an Interceptor which will make the
    request to our api call. If we had api key or user-agent needed to make our request, we would
    added to this interceptor.

    In this case, the requests are simplier.
 */
class QueryInterceptor @Inject constructor(): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url

        val url = originalUrl
            .newBuilder()
            .build()


        val request = original.newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)

    }
}