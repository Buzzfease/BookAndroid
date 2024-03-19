package com.buzz.bookandroid.network.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Book(
    val id: String,
    val name: String? = "",
    val author: String? = "",
    val publishYear: String? = "",
    val isbn: String? = ""
) : Parcelable
