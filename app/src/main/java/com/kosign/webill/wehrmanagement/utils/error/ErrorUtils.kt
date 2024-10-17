package com.kosign.webill.wehrmanagement.utils.error

import com.kosign.webill.wehrmanagement.utils.ResourceUtil

fun getErrorMessage(code: Int?, message: String?): ResourceUtil {
    return when(code) {
        ErrorCode.CONNECTION_ERROR -> {
            ResourceUtil.DynamicString("No internet connection")
        }
        ErrorCode.UNKNOWN_ERROR -> {
            ResourceUtil.DynamicString("Unknown error")
        }
        else -> {
            ResourceUtil.DynamicString(message.toString())
        }
    }
}