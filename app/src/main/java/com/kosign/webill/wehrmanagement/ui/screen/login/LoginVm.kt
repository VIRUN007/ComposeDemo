package com.kosign.webill.wehrmanagement.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kosign.webill.wehrmanagement.data.datasource.local.DataStore
import com.kosign.webill.wehrmanagement.data.model.Login
import com.kosign.webill.wehrmanagement.data.model.datastate.DataState
import com.kosign.webill.wehrmanagement.data.repository.LoginRepository
import com.kosign.webill.wehrmanagement.ui.screen.login.state.LoginState
import com.kosign.webill.wehrmanagement.utils.error.getErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginVm @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state : StateFlow<LoginState> = _state.asStateFlow()

    fun login(login: Login) {
        viewModelScope.launch {
            loginRepository.login(login).onEach { res ->
                when (res) {
                    is DataState.Loading -> {
                        _state.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }

                    is DataState.Success -> {
                        DataStore.AuthToken.value = res.data.payload?.token.toString()
                        _state.update {
                            it.copy(
                                data = res.data.payload,
                                isLoading = false
                            )
                        }
                    }

                    is DataState.Error -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = getErrorMessage(
                                    res.code,
                                    res.message
                                )
                            )
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun getSummary(){
        viewModelScope.launch {
            loginRepository.getSummary().onEach { res ->
                when (res) {
                    is DataState.Loading -> {
                        _state.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }

                    is DataState.Success -> {
                        println("Summary >>> ${res.data.payload?.leaveBalances}")
                        _state.update {
                            it.copy(
                                isLoading = false
                            )
                        }
                    }

                    is DataState.Error -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = getErrorMessage(
                                    res.code,
                                    res.message
                                )
                            )
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}