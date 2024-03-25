package com.yuvasai.nycschools.data.di

import com.yuvasai.nycschools.common.Constants
import com.yuvasai.nycschools.data.remote.SchoolDirectoryAPI
import com.yuvasai.nycschools.data.repository.SchoolDirectoryRepositoryImpl
import com.yuvasai.nycschools.domain.repository.SchoolDirectoryRepository
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
object AppModule {

    @Provides
    @Singleton
    fun providesFetchListItemsApi(): SchoolDirectoryAPI {
        val clientBuilder = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        clientBuilder.addInterceptor(loggingInterceptor)
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(clientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SchoolDirectoryAPI::class.java)
    }

    @Provides
    @Singleton
    fun providesListItemsRepositoryImpl(api: SchoolDirectoryAPI): SchoolDirectoryRepository {
        return SchoolDirectoryRepositoryImpl(api)
    }
}