package com.kosign.webill.wehrmanagement.data.repository

import com.kosign.webill.wehrmanagement.data.model.BaseModel
import com.kosign.webill.wehrmanagement.data.model.Login
import com.kosign.webill.wehrmanagement.data.model.LoginResponse
import com.kosign.webill.wehrmanagement.data.model.UserSummaryResponse
import com.kosign.webill.wehrmanagement.data.model.datastate.DataState
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun login(login: Login): Flow<DataState<BaseModel<LoginResponse>>>

    fun getSummary(): Flow<DataState<BaseModel<UserSummaryResponse>>>
}