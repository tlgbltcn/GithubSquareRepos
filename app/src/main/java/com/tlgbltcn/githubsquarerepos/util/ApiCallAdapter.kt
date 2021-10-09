package com.tlgbltcn.githubsquarerepos.util

import com.freeletics.flowredux.dsl.ChangeState
import retrofit2.Response
import java.lang.Exception

suspend fun <T, S> apiCall(
    onCall: suspend () -> Response<T>,
    onSuccess: ((T?) -> ChangeState<S>),
    onFailure: ((code: Int, message: String) -> ChangeState<S>)
): ChangeState<S> {
    return try {
        val result = onCall.invoke()
        if (result.isSuccessful) {
            onSuccess.invoke(result.body())
        } else {
            onFailure.invoke(result.code(), result.message())
        }
    } catch (e: Exception) {
        onFailure.invoke(-1, e.message ?: "")
    }
}