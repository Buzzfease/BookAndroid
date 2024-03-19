package com.buzz.bookandroid.network.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Book(
    val id: String,
    var name: String? = "",
    var author: String? = "",
    var publishYear: String? = "",
    var isbn: String? = ""
) : Parcelable
