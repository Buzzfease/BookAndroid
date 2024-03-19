package com.buzz.bookandroid.adapterdelegate.utils

internal data class NullablePayload<TYPE>(val value: TYPE?)

internal fun <TYPE> NullablePayload<TYPE>?.applyIfNonNull(action: (TYPE?) -> Unit) {
    if (this != null) {
        action(value)
    }
}
