package com.example.movie_db.data

import java.lang.Exception

/**
 * Represents the result of a HTTP request
 */
sealed class Result<out T: Any> {
    data class Success<out T: Any>(val data: T): Result<T>()
    data class Error(val exception: Exception): Result<Nothing>()
}