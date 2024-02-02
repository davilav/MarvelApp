package com.dfavilav.marvelapplication.di

import androidx.paging.ExperimentalPagingApi
import com.dfavilav.marvelapplication.data.local.MarvelDatabase
import com.dfavilav.marvelapplication.data.remote.MarvelApi
import com.dfavilav.marvelapplication.data.repository.RemoteDataSourceImpl
import com.dfavilav.marvelapplication.domain.repository.RemoteDataSource
import com.dfavilav.marvelapplication.util.Constants.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@ExperimentalPagingApi
@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): MarvelApi {
        return retrofit.create(MarvelApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        marvelApi: MarvelApi,
        marvelDatabase: MarvelDatabase
    ): RemoteDataSource {
        return RemoteDataSourceImpl(
            marvelApi, marvelDatabase
        )
    }


    private val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }
}