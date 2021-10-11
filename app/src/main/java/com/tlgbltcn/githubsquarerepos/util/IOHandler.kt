package com.tlgbltcn.githubsquarerepos.util

import retrofit2.Response
import java.lang.Exception

suspend fun <T> ioHandler(
    shouldFetch: suspend () -> Boolean,
    onQuery: suspend () -> T,
    onStoreData: suspend (T) -> Unit,
    onCall: suspend () -> Response<T>,
    onSuccess: suspend (T) -> Unit,
    onFailure: suspend ((code: Int, message: String) -> Unit)
) {
    return if (shouldFetch.invoke()) {
        try {
            val result = onCall.invoke()
            if (result.isSuccessful && result.body() != null) {
                onStoreData.invoke(result.body()!!)
                onSuccess.invoke(onQuery.invoke())
            } else {
                onFailure.invoke(result.code(), result.message())
            }
        } catch (e: Exception) {
            onFailure.invoke(-1, e.message ?: "")
        }
    } else {
        onSuccess.invoke(onQuery.invoke())
    }
}