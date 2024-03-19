package com.buzz.bookandroid.screen.booklist.view

import android.content.Context
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.buzz.bookandroid.common.arch.view.BaseFragment
import com.buzz.bookandroid.databinding.FragmentBookListBinding
import com.buzz.bookandroid.di.AppComponentHolder
import com.buzz.bookandroid.screen.booklist.model.BookListAction
import com.buzz.bookandroid.screen.booklist.model.BookListState
import com.buzz.bookandroid.screen.booklist.model.BookListViewModel
import com.buzz.bookandroid.screen.booklist.view.render.BookListRender
import javax.inject.Inject

internal class BookListFragment : BaseFragment<FragmentBookListBinding, BookListViewModel>(
    FragmentBookListBinding::inflate
) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var bookListRenderer: BookListRender

    override val viewModel by viewModels<BookListViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AppComponentHolder.getComponent().bookListComponent().create().inject(this)
    }

    override fun initRenderers(binding: FragmentBookListBinding) {
        with(binding) {
            bookListRenderer.init(recyclerView = recyclerView,
                onItemClick = { id ->
                    viewModel.doAction(BookListAction.OnBookItemClick(id))
                },
                onRetryClick = {
                    viewModel.doAction(BookListAction.LoadData)
                }
            )
        }
    }

    override fun observeStateOrEvent(viewModel: BookListViewModel) {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is BookListState.Data -> {
                    bookListRenderer.renderData(it.dataSource)
                }
                is BookListState.Loading -> {
                    bookListRenderer.renderLoading()
                }
                is BookListState.Error -> {
                    bookListRenderer.renderError()
                }
            }
        }
        viewModel.event.observe(viewLifecycleOwner) {

        }
        viewModel.doAction(BookListAction.LoadData)
    }

}