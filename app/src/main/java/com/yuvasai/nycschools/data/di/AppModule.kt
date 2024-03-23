package com.yuvasai.nycschools.data.di

import com.yuvasai.nycschools.common.Constants
import com.yuvasai.nycschools.data.remote.DirectoryAPI
import com.yuvasai.nycschools.data.repository.DirectoryRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesFetchListItemsApi(): DirectoryAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DirectoryAPI::class.java)
    }

    @Provides
    @Singleton
    fun providesListItemsRepositoryImpl(api: DirectoryAPI): DirectoryRepositoryImpl {
        return DirectoryRepositoryImpl(api)
    }
}