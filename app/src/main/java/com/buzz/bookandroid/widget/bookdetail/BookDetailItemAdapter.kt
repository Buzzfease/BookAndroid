package com.buzz.bookandroid.widget.bookdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.buzz.bookandroid.R
import com.buzz.bookandroid.adapterdelegate.adapter.AdapterDelegate
import com.buzz.bookandroid.adapterdelegate.viewholder.BaseViewHolder
import com.buzz.bookandroid.databinding.ItemBookDetailBinding

internal class BookDetailItemAdapter : AdapterDelegate<BookDetailItem> {

    override val viewType: Int
        get() = VIEW_TYPE

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<out BookDetailItem> {
        val binding = ItemBookDetailBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BookDetailItemViewHolder(
            llBookDetail = binding.llBookDetail
        )
    }

    companion object {
        val VIEW_TYPE = R.layout.item_book_list
    }
}

internal class BookDetailItemViewHolder(
    private val llBookDetail: LinearLayout
) : BaseViewHolder<BookDetailItem>(llBookDetail) {

    override fun onBind(item: BookDetailItem) = with(item) {
        setId(id)
        setName(name)
        setAuthor(author)
        setPublishYear(publishYear)
        setISBN(isbn)
    }

    override fun onPayload(item: BookDetailItem, payloads: List<Any>) {
        val listItemPayload = payloads.first() as BookDetailItem.Payload
        with(listItemPayload) {
            id?.let { setId(it) }
            name?.let { setName(it) }
            author?.let { setAuthor(it) }
            publishYear?.let { setPublishYear(it) }
            isbn?.let { setISBN(it) }
        }
    }

    private fun setId(id: String) {
        llBookDetail.findViewById<TextView>(R.id.tvId).text = id
    }

    private fun setName(name: String) {
        llBookDetail.findViewById<TextView>(R.id.tvName).text = name
    }

    private fun setAuthor(author: String) {
        llBookDetail.findViewById<TextView>(R.id.tvAuthor).text = author
    }

    private fun setPublishYear(publishYear: String) {
        llBookDetail.findViewById<TextView>(R.id.tvPublishYear).text = publishYear
    }

    private fun setISBN(isbn: String) {
        llBookDetail.findViewById<TextView>(R.id.tvISBN).text = isbn
    }

}