package com.tlgbltcn.githubsquarerepos.di

import android.content.Context
import coil.util.CoilUtils
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.tlgbltcn.githubsquarerepos.BuildConfig
import com.tlgbltcn.githubsquarerepos.data.remote.GithubService
import com.tlgbltcn.githubsquarerepos.util.TokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val MEDIA_TYPE = "application/json".toMediaType()

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }


    @Singleton
    @Provides
    fun provideTokenInterceptor(): TokenInterceptor = TokenInterceptor()

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        loggingInterceptor: HttpLoggingInterceptor,
        tokenInterceptor: TokenInterceptor
    ): OkHttpClient {
        return with(OkHttpClient.Builder()) {
            addInterceptor(loggingInterceptor)
            addInterceptor(tokenInterceptor)
            cache(CoilUtils.createDefaultCache(context))
            build()
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, json: Json): Retrofit {
        return with(Retrofit.Builder()) {
            baseUrl(BuildConfig.API_URL)
            client(okHttpClient)
            addConverterFactory(json.asConverterFactory(MEDIA_TYPE))
            build()
        }
    }

    @Singleton
    @Provides
    fun provideServices(retrofit: Retrofit): GithubService {
        return retrofit.create(GithubService::class.java)
    }
}