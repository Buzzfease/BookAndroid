package com.buzz.bookandroid.screen.booklist.view

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.buzz.bookandroid.R
import com.buzz.bookandroid.common.arch.view.BaseFragment
import com.buzz.bookandroid.common.extension.onNavigationResult
import com.buzz.bookandroid.common.wrapper.EditPageParamWrapper
import com.buzz.bookandroid.databinding.FragmentBookListBinding
import com.buzz.bookandroid.di.AppComponentHolder
import com.buzz.bookandroid.screen.bookdetail.view.BookDetailFragment.Companion.RESULT_DELETED
import com.buzz.bookandroid.screen.bookedit.view.BookEditFragment.Companion.RESULT_INSERTED
import com.buzz.bookandroid.screen.bookedit.view.BookEditFragment.Companion.RESULT_UPDATED
import com.buzz.bookandroid.screen.booklist.model.BookListAction
import com.buzz.bookandroid.screen.booklist.model.BookListEvent
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
            toolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.insertBook -> {
                        viewModel.doAction(BookListAction.OnBookInsertMenuClick)
                    }
                }
                false
            }
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
            when (it) {
                is BookListEvent.GoToDetailEvent -> {
                    findNavController().navigate(
                        BookListFragmentDirections.actionBookListFragmentToBookDetailsFragment(
                            it.data
                        )
                    )
                }
                is BookListEvent.GoToInsertBookEvent -> {
                    findNavController().navigate(
                        BookListFragmentDirections.actionBookListFragmentToBookEditFragment(
                            EditPageParamWrapper(
                                isUpdate = false,
                                it.book
                            )
                        )
                    )
                }
                is BookListEvent.BookUpdated -> {
                    Toast.makeText(requireContext(), getString(R.string.update_success), Toast.LENGTH_LONG).show()
                }
                is BookListEvent.BookInserted -> {
                    Toast.makeText(requireContext(), getString(R.string.insert_success), Toast.LENGTH_LONG).show()
                }
                is BookListEvent.BookDeleted -> {
                    Toast.makeText(requireContext(), getString(R.string.delete_success), Toast.LENGTH_LONG).show()
                }
            }
        }

        // book updated
        onNavigationResult(
            R.id.bookListFragment,
            RESULT_UPDATED
        ) { done: Boolean ->
            if (done) {
                viewModel.doAction(BookListAction.OnBookUpdated)
            }
        }

        // book inserted
        onNavigationResult(
            R.id.bookListFragment,
            RESULT_INSERTED
        ) { done: Boolean ->
            if (done) {
                viewModel.doAction(BookListAction.OnBookInserted)
            }
        }

        // book deleted
        onNavigationResult(
            R.id.bookListFragment,
            RESULT_DELETED
        ) { done: Boolean ->
            if (done) {
                viewModel.doAction(BookListAction.OnBookDeleted)
            }
        }

        viewModel.doAction(BookListAction.LoadData)
    }

}