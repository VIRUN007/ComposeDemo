package com.kosign.webill.wehrmanagement.ui.screen.login.state

import com.kosign.webill.wehrmanagement.data.model.LoginResponse
import com.kosign.webill.wehrmanagement.utils.ResourceUtil

data class LoginState(
    val isLoading: Boolean? = false,
    val data: LoginResponse? = null,
    val error: ResourceUtil? = null
)