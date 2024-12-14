package com.example.newsapptheme.domain.model

data class Resource<T>(
    var status: Status,
    var data: T? = null,
    var message: String? = null
) {
    val isSuccessful = status == Status.SUCCESS
    companion object {
        fun <T> success(data: T) = Resource(Status.SUCCESS, data)
        fun <T> error(data: T?, message: String) = Resource(Status.ERROR, data, message)
        fun <T> loading(data: T?) = Resource(Status.LOADING, data)
    }
}

enum class Status{
    SUCCESS, ERROR, LOADING;
}