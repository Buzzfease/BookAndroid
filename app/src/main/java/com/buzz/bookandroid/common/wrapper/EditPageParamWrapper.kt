package com.buzz.bookandroid.common.wrapper

import android.os.Parcelable
import androidx.annotation.Keep
import com.buzz.bookandroid.network.model.Book
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class EditPageParamWrapper(
    val isUpdate: Boolean? = false,
    val book: Book? = null
) : Parcelable