package com.example.kotlin1lesson1.di

import com.example.kotlin1lesson1.data.network.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    val retrofitClient = RetrofitClient()

    @Provides
    @Singleton
    fun provideCharacterApiService() = retrofitClient.characterRetrofitClient()

    @Provides
    @Singleton
    fun provideEpisodeApiService() = retrofitClient.episodeRetrofitClient()

    @Provides
    @Singleton
    fun provideLocationApiService() = retrofitClient.locationRetrofitClient()
}