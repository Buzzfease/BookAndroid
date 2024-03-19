package com.buzz.bookandroid.screen.bookdetail.view

import android.content.Context
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.buzz.bookandroid.common.arch.view.BaseFragment
import com.buzz.bookandroid.databinding.FragmentBookDetailBinding
import com.buzz.bookandroid.di.AppComponentHolder
import com.buzz.bookandroid.screen.bookdetail.model.BookDetailAction
import com.buzz.bookandroid.screen.bookdetail.model.BookDetailState
import com.buzz.bookandroid.screen.bookdetail.model.BookDetailViewModel
import com.buzz.bookandroid.screen.bookdetail.view.render.BookDetailRender
import javax.inject.Inject

internal class BookDetailFragment : BaseFragment<FragmentBookDetailBinding, BookDetailViewModel>(
    FragmentBookDetailBinding::inflate
) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var bookDetailRender: BookDetailRender

    override val viewModel by viewModels<BookDetailViewModel> { viewModelFactory }

    private val args: BookDetailFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AppComponentHolder.getComponent().bookDetailComponent().create().inject(this)
    }

    override fun initRenderers(binding: FragmentBookDetailBinding) {
        with(binding) {
            bookDetailRender.init(recyclerView = recyclerView,
                onRetryClick = {
                    viewModel.doAction(BookDetailAction.LoadData(args.id))
                }
            )
        }
    }

    override fun observeStateOrEvent(viewModel: BookDetailViewModel) {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is BookDetailState.Data -> {
                    bookDetailRender.renderData(it.dataSource)
                }
                is BookDetailState.Loading -> {
                    bookDetailRender.renderLoading()
                }
                is BookDetailState.Error -> {
                    bookDetailRender.renderError()
                }
            }
        }
        viewModel.event.observe(viewLifecycleOwner) {

        }

        viewModel.doAction(BookDetailAction.LoadData(args.id))
    }

}