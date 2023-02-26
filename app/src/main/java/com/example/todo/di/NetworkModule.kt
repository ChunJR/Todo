package com.example.todo.di

import com.example.todo.core.data.repository.TodoService
import com.example.todo.util.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    factory { getLoggingInterceptor() }

    factory(named("without_custom_interceptor")) {
        getOkHttpClientBuilder( get() )
    }

    single(named("without_custom_interceptor")) {
        getRetrofit(get(named("without_custom_interceptor")))
    }

    single { get<Retrofit>(named("without_custom_interceptor")).create(TodoService::class.java) }
}

fun getRetrofit(okHttpClientBuilder: OkHttpClient.Builder): Retrofit {
    return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client((okHttpClientBuilder).build())
            .build()
}

fun getOkHttpClientBuilder(
        loggingInterceptor: HttpLoggingInterceptor
): OkHttpClient.Builder {
    val httpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
    if (BuildConfig.DEBUG) {
        httpClient.addInterceptor(loggingInterceptor)
    }
    return httpClient
}

fun getLoggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    return logging
}