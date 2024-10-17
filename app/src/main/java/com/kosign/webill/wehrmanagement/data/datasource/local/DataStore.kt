package com.kosign.webill.wehrmanagement.data.datasource.local

sealed class DataStore(var value: String) {

    companion object{
        const val TOKEN = ""
    }
    object AuthToken : DataStore(TOKEN)
}