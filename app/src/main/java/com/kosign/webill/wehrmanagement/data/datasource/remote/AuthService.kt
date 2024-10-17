package com.kosign.webill.wehrmanagement.data.datasource.remote

import com.kosign.webill.wehrmanagement.data.model.BaseModel
import com.kosign.webill.wehrmanagement.data.model.Login
import com.kosign.webill.wehrmanagement.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/v1/auth/credentials")
    suspend fun login(
        @Body login: Login
    ): Response<BaseModel<LoginResponse>>
}