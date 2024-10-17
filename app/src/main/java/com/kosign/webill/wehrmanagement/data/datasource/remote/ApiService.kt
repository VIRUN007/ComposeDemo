package com.kosign.webill.wehrmanagement.data.datasource.remote

import com.kosign.webill.wehrmanagement.data.model.BaseModel
import com.kosign.webill.wehrmanagement.data.model.Login
import com.kosign.webill.wehrmanagement.data.model.LoginResponse
import com.kosign.webill.wehrmanagement.data.model.UserSummaryResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("api/summary")
    suspend fun getSummary() : Response<BaseModel<UserSummaryResponse>>
}