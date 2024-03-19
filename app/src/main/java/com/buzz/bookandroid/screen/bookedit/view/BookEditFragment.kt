package com.buzz.bookandroid.screen.bookedit.view

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.buzz.bookandroid.R
import com.buzz.bookandroid.common.arch.view.BaseFragment
import com.buzz.bookandroid.common.extension.setNavigationResult
import com.buzz.bookandroid.databinding.FragmentBookEditBinding
import com.buzz.bookandroid.di.AppComponentHolder
import com.buzz.bookandroid.network.model.Book
import com.buzz.bookandroid.screen.bookedit.model.BookEditAction
import com.buzz.bookandroid.screen.bookedit.model.BookEditEvent
import com.buzz.bookandroid.screen.bookedit.model.BookEditState
import com.buzz.bookandroid.screen.bookedit.model.BookEditViewModel
import com.buzz.bookandroid.screen.bookedit.view.render.BookEditRender
import com.buzz.bookandroid.screen.bookedit.view.render.BookEditSubmitButtonRender
import javax.inject.Inject

internal class BookEditFragment : BaseFragment<FragmentBookEditBinding, BookEditViewModel>(
    FragmentBookEditBinding::inflate
) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var bookEditRenderer: BookEditRender

    @Inject
    lateinit var bookEditSubmitButtonRender: BookEditSubmitButtonRender

    override val viewModel by viewModels<BookEditViewModel> { viewModelFactory }

    private val args: BookEditFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AppComponentHolder.getComponent().bookEditComponent().create().inject(this)
    }

    override fun initRenderers(binding: FragmentBookEditBinding) {
        with(binding) {
            bookEditSubmitButtonRender.init(
                submitButton = tvSubmit,
                onSubmitButtonClick = {
                    if (args.editPageParam.isUpdate == true) {
                        viewModel.doAction(BookEditAction.OnUpdateButtonClick(it))
                    } else {
                        viewModel.doAction(BookEditAction.OnInsertButtonClick(it))
                    }
                }
            )
            bookEditRenderer.init(
                recyclerView = recyclerView,
                onEditStateChange = { isActive: Boolean, book: Book ->
                    bookEditSubmitButtonRender.apply {
                        setBook(book)
                        if (isActive) {
                            renderSubmitButtonEnable()
                        } else {
                            renderSubmitButtonDisable()
                        }
                    }
                }
            )
        }
    }

    override fun observeStateOrEvent(viewModel: BookEditViewModel) {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is BookEditState.Data -> {
                    bookEditRenderer.renderData(it.dataSource)
                }
                is BookEditState.Loading -> {
                    bookEditRenderer.renderLoading()
                }
            }
        }
        viewModel.event.observe(viewLifecycleOwner) {
            when (it) {
                is BookEditEvent.UpdateSuccessEvent -> {
                    setNavigationResult(R.id.bookListFragment, RESULT_UPDATED, true)
                    findNavController().popBackStack(R.id.bookListFragment, false)
                }
                is BookEditEvent.InsertSuccessEvent -> {
                    setNavigationResult(R.id.bookListFragment, RESULT_INSERTED, true)
                    findNavController().popBackStack(R.id.bookListFragment, false)
                }
                is BookEditEvent.ShowErrorBannerEvent -> {
                    Toast.makeText(requireContext(), it.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }

        args.editPageParam.apply {
            if (isUpdate == true && book != null) {
                viewModel.doAction(BookEditAction.LoadData(book))
            } else {
                viewModel.doAction(BookEditAction.LoadData(
                    Book(
                        id = "",
                        name = "",
                        author = "",
                        publishYear = "",
                        isbn = ""
                    )
                ))
            }
        }
    }

    companion object {
        const val RESULT_UPDATED = "RESULT_UPDATED"
        const val RESULT_INSERTED = "RESULT_INSERTED"
    }

}