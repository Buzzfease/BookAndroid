package com.buzz.bookandroid.screen.bookdetail.view

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.buzz.bookandroid.R
import com.buzz.bookandroid.common.arch.view.BaseFragment
import com.buzz.bookandroid.common.extension.setNavigationResult
import com.buzz.bookandroid.common.wrapper.EditPageParamWrapper
import com.buzz.bookandroid.databinding.FragmentBookDetailBinding
import com.buzz.bookandroid.di.AppComponentHolder
import com.buzz.bookandroid.screen.bookdetail.model.BookDetailAction
import com.buzz.bookandroid.screen.bookdetail.model.BookDetailEvent
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
            toolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.updateBook -> {
                        viewModel.doAction(BookDetailAction.OnBookUpdateMenuClick)
                    }
                    R.id.deleteBook -> {
                        viewModel.doAction(BookDetailAction.OnBookDeleteMenuClick)
                    }
                }
                false
            }
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
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
            when (it) {
                is BookDetailEvent.GoToUpdateBookEvent -> {
                    findNavController().navigate(
                        BookDetailFragmentDirections.actionBookDetailsFragmentToBookEditFragment(
                            EditPageParamWrapper(
                                isUpdate = true,
                                it.book
                            )
                        )
                    )
                }
                is BookDetailEvent.ShowDeleteWaringPopupEvent -> {
                    AlertDialog.Builder(requireContext()).apply {
                        setTitle(getString(R.string.confirm_delete))
                        setMessage(getString(R.string.confirm_delete_content))
                        setPositiveButton(getString(R.string.confirm_positive_button),
                            DialogInterface.OnClickListener { _, _ ->
                                viewModel.doAction(BookDetailAction.DeleteBook(it.id))
                            })
                        setNegativeButton(getString(R.string.confirm_negative_button),
                            DialogInterface.OnClickListener { dialog, _ ->
                                dialog.dismiss()
                            })
                        create().show()
                    }
                }
                is BookDetailEvent.DeleteSuccessEvent -> {
                    setNavigationResult(R.id.bookListFragment, RESULT_DELETED, true)
                    findNavController().popBackStack(R.id.bookListFragment, false)
                }
                is BookDetailEvent.DeleteFailedEvent -> {
                    Toast.makeText(requireContext(), getString(R.string.delete_failed), Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.doAction(BookDetailAction.LoadData(args.id))
    }

    companion object {
        const val RESULT_DELETED = "RESULT_DELETED"
    }

}