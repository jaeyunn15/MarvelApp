package com.project.marvelapp.di

import com.project.marvelapp.api.CharacterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    val BASE_URL = "https://gateway.marvel.com/"

    @Provides
    @Singleton
    fun provideAuthInterceptor() = AuthInterceptor()

    @Singleton
    @Provides
    fun provideHttpClient(
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS

        return OkHttpClient
            .Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(interceptor)
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideCharacterService(retrofit: Retrofit): CharacterService = retrofit.create(
        CharacterService::class.java)
}