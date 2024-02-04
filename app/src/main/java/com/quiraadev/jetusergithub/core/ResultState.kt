package com.quiraadev.jetusergithub.core

sealed class ResultState<out T: Any?>() {
    data object Loading: ResultState<Nothing>()
    data class Error(val errorMessage: String) : ResultState<Nothing>()
    data class Success<out T: Any>(val data: Any): ResultState<T>()
}