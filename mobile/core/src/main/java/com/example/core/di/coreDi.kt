package com.example.core.di

import com.example.core.BaseUrlService
import com.example.core.BaseUrlServiceImpl
import com.example.core.retrofit.RetrofitBuilderApp
import com.example.core.retrofit.RetrofitBuilderAppImpl
import okhttp3.OkHttpClient
import org.koin.dsl.module

val coreModule = module {
    single { OkHttpClient.Builder().build() }
    single<BaseUrlService> { BaseUrlServiceImpl() }
    single<RetrofitBuilderApp> { RetrofitBuilderAppImpl(okHttpClient = get()) }
    single { get<RetrofitBuilderApp>().createRetrofit() }
}