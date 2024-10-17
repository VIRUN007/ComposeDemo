package com.kosign.webill.wehrmanagement.data.model.datastate

/**
 * Data state for processing api response Loading, Success and Error
 */
sealed class DataState<out R> {
    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val code: Int? = null, val message: String? = null) : DataState<Nothing>()
    data object Loading : DataState<Nothing>()
}