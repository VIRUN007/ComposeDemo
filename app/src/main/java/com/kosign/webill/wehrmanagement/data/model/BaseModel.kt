package com.kosign.webill.wehrmanagement.data.model

data class BaseModel<T> (
    val status: String,
    val message: String,
    val payload: T?,
    val date: String
)