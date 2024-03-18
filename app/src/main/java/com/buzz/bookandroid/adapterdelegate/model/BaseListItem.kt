package com.buzz.bookandroid.adapterdelegate.model

interface BaseListItem {

    val id: String

    val viewType: Int

    fun calculatePayload(listItem: BaseListItem): Any?

    fun areItemsTheSame(listItem: BaseListItem): Boolean =
        id == listItem.id && viewType == listItem.viewType
}