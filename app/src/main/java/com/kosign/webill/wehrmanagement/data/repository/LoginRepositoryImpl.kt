package com.kosign.webill.wehrmanagement.data.repository

import android.util.Log
import com.kosign.webill.wehrmanagement.data.datasource.remote.ApiService
import com.kosign.webill.wehrmanagement.data.datasource.remote.AuthService
import com.kosign.webill.wehrmanagement.data.model.BaseModel
import com.kosign.webill.wehrmanagement.data.model.Login
import com.kosign.webill.wehrmanagement.data.model.LoginResponse
import com.kosign.webill.wehrmanagement.data.model.UserSummaryResponse
import com.kosign.webill.wehrmanagement.data.model.datastate.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import java.net.ConnectException
import java.net.SocketException
import java.net.UnknownHostException
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class LoginRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    private val apiService: ApiService
) : LoginRepository{
    override fun login(login: Login): Flow<DataState<BaseModel<LoginResponse>>> = flow {
        emit(DataState.Loading)
        try {
            val response = authService.login(login)
            if (response.isSuccessful){
                val responseBody = response.body()
                if (responseBody == null)
                    emit(DataState.Error(code = response.code(),message = response?.message()))
                else{
                    emit(DataState.Success(responseBody))
                }

            }else{
                var errorMessage: String? = null
                var errorCode: Int? = null
                val jsonObject = JSONObject(response.errorBody()!!.string())
//                val jsonError = jsonObject.getJSONObject("status")
                try {
                    errorCode = jsonObject.getInt("status")
                    errorMessage = jsonObject.getString("message")
                }catch (e: Exception) {
                    e.printStackTrace()
                }
                emit(DataState.Error(code = errorCode, message = errorMessage))
            }

        } catch (e: Exception) {
            if (e is CancellationException) {
                emit(DataState.Error(message = e.message))
            } else {
                if (e is UnknownHostException || e is ConnectException || e is SocketException) {
                    emit(DataState.Error(message = e.message))
                }
                emit(DataState.Error(message = e.message))
            }
        }
    }

    override fun getSummary(): Flow<DataState<BaseModel<UserSummaryResponse>>> = flow {
        emit(DataState.Loading)
        try {
            val response = apiService.getSummary()
            if (response.isSuccessful){
                val responseBody = response.body()
                if (responseBody == null)
                    emit(DataState.Error(code = response.code(),message = response?.message()))
                else{
                    emit(DataState.Success(responseBody))
                }

            }else{
                var errorMessage: String? = null
                var errorCode: Int? = null
                val jsonObject = JSONObject(response.errorBody()!!.string())
//                val jsonError = jsonObject.getJSONObject("status")
                try {
                    errorCode = jsonObject.getInt("status")
                    errorMessage = jsonObject.getString("message")
                }catch (e: Exception) {
                    e.printStackTrace()
                }
                emit(DataState.Error(code = errorCode, message = errorMessage))
            }

        } catch (e: Exception) {
            if (e is CancellationException) {
                emit(DataState.Error(message = e.message))
            } else {
                if (e is UnknownHostException || e is ConnectException || e is SocketException) {
                    emit(DataState.Error(message = e.message))
                }
                emit(DataState.Error(message = e.message))
            }
        }
    }
}