package com.buzz.bookandroid.screen.booklist.view

import android.content.Context
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.buzz.bookandroid.common.arch.view.BaseFragment
import com.buzz.bookandroid.databinding.FragmentBookListBinding
import com.buzz.bookandroid.di.AppComponentHolder
import com.buzz.bookandroid.screen.booklist.model.BookListViewModel
import javax.inject.Inject

internal class BookListFragment : BaseFragment<FragmentBookListBinding, BookListViewModel>(
    FragmentBookListBinding::inflate
) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModel by viewModels<BookListViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AppComponentHolder.getComponent().bookListComponent().create().inject(this)
    }

    override fun initRenderers(binding: FragmentBookListBinding) {

    }

    override fun observeStateOrEvent(viewModel: BookListViewModel) {

    }

}