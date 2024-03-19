package com.buzz.bookandroid.widget.bookedit

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.widget.addTextChangedListener
import com.buzz.bookandroid.R
import com.buzz.bookandroid.adapterdelegate.adapter.AdapterDelegate
import com.buzz.bookandroid.adapterdelegate.viewholder.BaseViewHolder
import com.buzz.bookandroid.databinding.ItemBookEditBinding
import com.buzz.bookandroid.network.model.Book

internal class BookEditItemAdapter(
    private val onEditStateChange: (isActive: Boolean, book: Book) -> Unit
) : AdapterDelegate<BookEditItem> {

    override val viewType: Int
        get() = VIEW_TYPE

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<out BookEditItem> {
        val binding = ItemBookEditBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BookEditItemViewHolder(
            llBookEdit = binding.llBookEdit,
            onEditStateChange = onEditStateChange
        )
    }

    companion object {
        val VIEW_TYPE = R.layout.item_book_edit
    }
}

internal class BookEditItemViewHolder(
    private val onEditStateChange: (isActive: Boolean, book: Book) -> Unit,
    private val llBookEdit: LinearLayout,
) : BaseViewHolder<BookEditItem>(llBookEdit) {

    private var book = Book(
        id = "",
        name = "",
        author = "",
        publishYear = "",
        isbn = ""
    )

    override fun onBind(item: BookEditItem) = with(item) {
        constructBook(item.id)
        setName(name)
        setAuthor(author)
        setPublishYear(publishYear)
        setISBN(isbn)
    }

    override fun onPayload(item: BookEditItem, payloads: List<Any>) {
        val listItemPayload = payloads.first() as BookEditItem.Payload
        with(listItemPayload) {
            name?.let { setName(it) }
            author?.let { setAuthor(it) }
            publishYear?.let { setPublishYear(it) }
            isbn?.let { setISBN(it) }
        }
    }

    private fun setName(name: String) {
        llBookEdit.findViewById<EditText>(R.id.edName).apply {
            setText(name)
            addTextChangedListener {
                onEditStateChange.invoke(
                    validateSubmitButtonActive(),
                    updateBook()
                )
            }
        }
    }

    private fun setAuthor(author: String) {
        llBookEdit.findViewById<EditText>(R.id.edAuthor).apply {
            setText(author)
            addTextChangedListener {
                onEditStateChange.invoke(
                    validateSubmitButtonActive(),
                    updateBook()
                )
            }
        }
    }

    private fun setPublishYear(publishYear: String) {
        llBookEdit.findViewById<EditText>(R.id.edPublishYear).apply {
            setText(publishYear)
            addTextChangedListener {
                onEditStateChange.invoke(
                    validateSubmitButtonActive(),
                    updateBook()
                )
            }
        }
    }

    private fun setISBN(isbn: String) {
        llBookEdit.findViewById<EditText>(R.id.edISBN).apply {
            setText(isbn)
            addTextChangedListener {
                onEditStateChange.invoke(
                    validateSubmitButtonActive(),
                    updateBook()
                )
            }
        }
    }

    private fun constructBook(id: String) {
        book = Book(
            id = id,
            name = "",
            author = "",
            publishYear = "",
            isbn = ""
        )
    }

    private fun updateBook(): Book {
       return book.apply {
            name = llBookEdit.findViewById<EditText>(R.id.edName).text.toString()
            author = llBookEdit.findViewById<EditText>(R.id.edAuthor).text.toString()
            publishYear = llBookEdit.findViewById<EditText>(R.id.edPublishYear).text.toString()
            isbn = llBookEdit.findViewById<EditText>(R.id.edISBN).text.toString()
        }
    }


    private fun validateSubmitButtonActive(): Boolean =
        llBookEdit.findViewById<EditText>(R.id.edName).text.isNotEmpty()
                && llBookEdit.findViewById<EditText>(R.id.edAuthor).text.isNotEmpty()
                && llBookEdit.findViewById<EditText>(R.id.edPublishYear).text.isNotEmpty()
                && llBookEdit.findViewById<EditText>(R.id.edISBN).text.isNotEmpty()



}