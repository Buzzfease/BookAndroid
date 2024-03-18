package com.buzz.bookandroid.widget

import android.os.Parcelable
import androidx.annotation.Keep
import com.buzz.bookandroid.adapterdelegate.model.BaseListItem
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
class BookListItem(
    override val id: String
) : BaseListItem, Parcelable {

    override val viewType: Int = 0

    override fun calculatePayload(listItem: BaseListItem): Any = Unit

}
