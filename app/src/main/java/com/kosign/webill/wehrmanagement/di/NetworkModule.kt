package com.kosign.webill.wehrmanagement.di

import com.kosign.webill.wehrmanagement.data.datasource.local.DataStore
import com.kosign.webill.wehrmanagement.data.datasource.remote.ApiService
import com.kosign.webill.wehrmanagement.data.datasource.remote.ApiURL
import com.kosign.webill.wehrmanagement.data.datasource.remote.AuthService
import com.kosign.webill.wehrmanagement.di.qualifier.ApiRetrofit
import com.kosign.webill.wehrmanagement.di.qualifier.ApiUrl
import com.kosign.webill.wehrmanagement.di.qualifier.AuthRetrofit
import com.kosign.webill.wehrmanagement.di.qualifier.AuthUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    /**
     * Provides BaseUrl as string
     */
    @AuthUrl
    @Singleton
    @Provides
    fun provideAuthUrl(): String {
        return ApiURL.AUTH_URL
    }

    @ApiUrl
    @Singleton
    @Provides
    fun provideApiUrl(): String {
        return ApiURL.API_URL
    }

    @Singleton
    @Provides
    fun provideHeaderInterceptor() : Interceptor = Interceptor { chain ->
        val originalRequest = chain.request()

        // Put your token from login
        val token = DataStore.AuthToken.value;
        // Add headers dynamically
        val newRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $token")
            .header("Content-Type", "application/json")
            .build()

        chain.proceed(newRequest)
    }

    /**
     * Provides LoggingInterceptor for api information
     */
    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    /**
     * Provides custom OkkHttp
     */
    @Singleton
    @Provides
    fun provideOkHttpClient(headerInterceptor: Interceptor, loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient().newBuilder()

        okHttpClient.callTimeout(40, TimeUnit.SECONDS)
        okHttpClient.connectTimeout(40, TimeUnit.SECONDS)
        okHttpClient.readTimeout(40, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(40, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(headerInterceptor)
        okHttpClient.addInterceptor(loggingInterceptor)
        okHttpClient.build()
        return okHttpClient.build()
    }
    /**
     * Provides converter factory for retrofit
     */
    @Singleton
    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }
    /**
     * Provides ApiServices client for Retrofit
     */
    @Singleton
    @AuthRetrofit
    @Provides
    fun provideAuthRetrofitClient(
        @AuthUrl baseUrl: String,
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Singleton
    @ApiRetrofit
    @Provides
    fun provideRetrofitClient(
        @ApiUrl baseUrl: String,
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }
    /**
     * Provides Api Service using retrofit
     */
    @Singleton
    @Provides
    fun provideAuthApiService(@AuthRetrofit retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    /**
     * Provides Api Service using retrofit
     */
    @Singleton
    @Provides
    fun provideRestApiService(@ApiRetrofit retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
