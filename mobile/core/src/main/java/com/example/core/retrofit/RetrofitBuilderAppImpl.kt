package com.example.core.retrofit

import com.example.core.BaseUrlServiceImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilderAppImpl(
    private val okHttpClient: OkHttpClient
) : RetrofitBuilderApp{

    override fun createRetrofit(): Retrofit {
        val gson: Gson by lazy { GsonBuilder().create() }

        return Retrofit.Builder()
            .baseUrl(BaseUrlServiceImpl().baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}