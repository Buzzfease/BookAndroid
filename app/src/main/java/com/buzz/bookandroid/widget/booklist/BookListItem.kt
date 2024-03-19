package com.buzz.bookandroid.widget.booklist

import android.os.Parcelable
import androidx.annotation.Keep
import com.buzz.bookandroid.adapterdelegate.model.BaseListItem
import com.buzz.bookandroid.adapterdelegate.utils.payload
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
class BookListItem(
    override val id: String,
    val name: String,
    val author: String,
    val publishYear: String,
    val isbn: String
) : BaseListItem, Parcelable {

    override val viewType: Int = BookListItemAdapter.VIEW_TYPE

    override fun calculatePayload(listItem: BaseListItem): Any? {
        return (listItem as? BookListItem)?.let { other ->
            Payload(
                id = payload(other.id, id),
                name = payload(other.name, name),
                author = payload(other.author, author),
                publishYear = payload(other.publishYear, publishYear),
                isbn = payload(other.isbn, isbn)
            )
        }
    }

    @Keep
    data class Payload(
        val id: String?,
        val name: String?,
        val author: String?,
        val publishYear: String?,
        val isbn: String?
    )

}
