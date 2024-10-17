package com.kosign.webill.wehrmanagement.utils

import android.content.Context
import androidx.annotation.StringRes

sealed class ResourceUtil {
    data class DynamicString(
        val value: String
    ): ResourceUtil()

    class StringResource(
        @StringRes val id: Int,
        vararg val args: Any
    ): ResourceUtil()

    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> context.getString(id, *args)
        }
    }
}