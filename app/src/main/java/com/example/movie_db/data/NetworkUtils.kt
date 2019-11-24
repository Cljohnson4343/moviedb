package com.example.movie_db.data

import java.io.IOException

/**
 * Wraps an API call in a try/catch. If exception is thrown, then [Result.Error] is created based
 * on the [errorMessage]
 */
suspend fun <T : Any> apiCall(call: suspend () -> Result<T>, errorMessage: String): Result<T> {
    return try {
        call()
    } catch (e: Exception) {
        Result.Error(IOException(errorMessage, e))
    }
}
