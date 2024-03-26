package com.yuvasai.nycschools.data.di

import com.yuvasai.nycschools.data.remote.SchoolDirectoryAPI
import com.yuvasai.nycschools.domain.repository.SchoolDirectoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.mockito.Mockito.mock
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Singleton
    fun providesMockSchoolDirectoryAPI(): SchoolDirectoryAPI {
        // Create a mock instance of the SchoolDirectoryAPI using Mockito or any mocking library
        return mock(SchoolDirectoryAPI::class.java)
    }

    @Provides
    @Singleton
    fun providesMockSchoolDirectoryRepository(api: SchoolDirectoryAPI): SchoolDirectoryRepository {
        // Create a mock instance of the SchoolDirectoryRepository using Mockito or any mocking library
        return mock(SchoolDirectoryRepository::class.java)
    }
}