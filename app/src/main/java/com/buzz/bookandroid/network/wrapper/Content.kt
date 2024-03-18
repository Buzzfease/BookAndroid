package com.buzz.bookandroid.network.wrapper

sealed class Content<T : Any> {
    data class Data<T : Any>(val data: T) : Content<T>()
    data class Error<T : Any>(val exception: Exception) : Content<T>()
}

suspend fun <T : Any> asContent(
    block: suspend () -> T
): Content<T> = try {
    block().data
} catch (e: Exception) {
    error(e)
}

fun <T : Any> Content<T>.getData() = (this as? Content.Data)?.data

private val <T : Any> T.data get() = Content.Data(this)
private fun <T : Any> error(exception: Exception) = Content.Error<T>(exception)
