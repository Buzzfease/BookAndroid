package com.buzz.bookandroid.widget.booklist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.buzz.bookandroid.R
import com.buzz.bookandroid.adapterdelegate.adapter.AdapterDelegate
import com.buzz.bookandroid.adapterdelegate.viewholder.BaseViewHolder
import com.buzz.bookandroid.databinding.ItemBookListBinding

internal class BookListItemAdapter(
    private val onItemClick: (id: String) -> Unit
) : AdapterDelegate<BookListItem> {

    override val viewType: Int
        get() = VIEW_TYPE

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<out BookListItem> {
        val binding = ItemBookListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BookListItemViewHolder(
            llBookList = binding.llBookList,
            onItemClick = onItemClick
        )
    }

    companion object {
        val VIEW_TYPE = R.layout.item_book_list
    }
}

internal class BookListItemViewHolder(
    private val llBookList: LinearLayout,
    private val onItemClick: (String) -> Unit
) : BaseViewHolder<BookListItem>(llBookList) {

    override fun onBind(item: BookListItem) = with(item) {
        setName(name)
        setAuthor(author)
        setPublishYear(publishYear)
        setISBN(isbn)
        setOnClickAction(this)
    }

    override fun onPayload(item: BookListItem, payloads: List<Any>) {
        val listItemPayload = payloads.first() as BookListItem.Payload
        with(listItemPayload) {
            name?.let { setName(it) }
            author?.let { setAuthor(it) }
            publishYear?.let { setPublishYear(it) }
            isbn?.let { setISBN(it) }
        }
    }

    private fun setName(name: String) {
        llBookList.findViewById<TextView>(R.id.tvName).text = name
    }

    private fun setAuthor(author: String) {
        llBookList.findViewById<TextView>(R.id.tvAuthor).text = author
    }

    private fun setPublishYear(publishYear: String) {
        llBookList.findViewById<TextView>(R.id.tvPublishYear).text = publishYear
    }

    private fun setISBN(isbn: String) {
        llBookList.findViewById<TextView>(R.id.tvISBN).text = isbn
    }

    private fun setOnClickAction(item: BookListItem) =
        llBookList.setOnClickListener {
            onItemClick(item.id)
        }
}
