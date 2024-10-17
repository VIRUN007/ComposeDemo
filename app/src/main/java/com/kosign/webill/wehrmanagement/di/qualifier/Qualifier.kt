package com.kosign.webill.wehrmanagement.di.qualifier

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthUrl

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiUrl