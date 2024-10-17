package com.kosign.webill.wehrmanagement.di

import com.kosign.webill.wehrmanagement.data.datasource.remote.ApiService
import com.kosign.webill.wehrmanagement.data.datasource.remote.AuthService
import com.kosign.webill.wehrmanagement.data.repository.LoginRepository
import com.kosign.webill.wehrmanagement.data.repository.LoginRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    /**
     * Provides RemoteDataRepository for access api service method
     */
    @Singleton
    @Provides
    fun provideLoginRepository(
        authService: AuthService,
        apiService: ApiService
    ): LoginRepository {
        return LoginRepositoryImpl(
            authService,
            apiService
        )
    }

}