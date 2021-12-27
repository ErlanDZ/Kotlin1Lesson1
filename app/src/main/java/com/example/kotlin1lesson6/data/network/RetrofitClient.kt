package com.example.kotlin1lesson6.data.network

import com.example.kotlin1lesson6.common.constants.Constants
import com.example.kotlin1lesson6.data.network.apiservices.CharacterApiService
import com.example.kotlin1lesson6.data.network.apiservices.EpisodeApiService
import com.example.kotlin1lesson6.data.network.apiservices.LocationApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class RetrofitClient {

    var okHttpClient: OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(provideLoggingInterceptor())
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

     fun characterRetrofitClient(): CharacterApiService {
        return retrofit.create(CharacterApiService::class.java)
    }

     fun locationRetrofitClient(): LocationApiService {
        return retrofit.create(LocationApiService::class.java)
    }

     fun episodeRetrofitClient(): EpisodeApiService {
        return retrofit.create(EpisodeApiService::class.java)
    }

}