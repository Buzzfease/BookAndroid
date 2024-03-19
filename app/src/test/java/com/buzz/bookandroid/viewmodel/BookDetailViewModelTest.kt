package com.buzz.bookandroid.viewmodel

import com.buzz.bookandroid.MockRepository
import com.buzz.bookandroid.core.CoroutineExtensionTest
import com.buzz.bookandroid.core.InstantTaskExecutorExtension
import com.buzz.bookandroid.core.observeValues
import com.buzz.bookandroid.network.repository.NetworkRepository
import com.buzz.bookandroid.screen.bookdetail.model.BookDetailAction
import com.buzz.bookandroid.screen.bookdetail.model.BookDetailModel
import com.buzz.bookandroid.screen.bookdetail.model.BookDetailReducer
import com.buzz.bookandroid.screen.bookdetail.model.BookDetailState
import com.buzz.bookandroid.screen.bookdetail.model.BookDetailViewModel
import com.buzz.bookandroid.withBookDetailError
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(
    value = [
        CoroutineExtensionTest::class,
        InstantTaskExecutorExtension::class
    ]
)
class BookDetailViewModelTest {

    private val mockRepository get() = MockRepository()

    @Test
    fun `test action load data success`() {
        val viewModel = buildViewModel(
            mockRepository
        )
        val states = viewModel.state.observeValues()
        viewModel.doAction(BookDetailAction.LoadData("1"))

        Truth.assertThat(states[0]).isEqualTo(BookDetailState.Loading)
        with(states[1] as BookDetailState.Data) {
            Truth.assertThat(this.dataSource).isNotEmpty()
        }
    }

    @Test
    fun `test action load data error`() {
        val viewModel = buildViewModel(
            mockRepository.withBookDetailError()
        )
        val states = viewModel.state.observeValues()
        viewModel.doAction(BookDetailAction.LoadData("1"))

        val result = listOf(
            BookDetailState.Loading,
            BookDetailState.Error
        )

        Truth.assertThat(states).isEqualTo(result)
    }

    private fun buildViewModel(
        repository: NetworkRepository,
    ): BookDetailViewModel {
        return BookDetailViewModel(
            initialState = BookDetailState.Loading,
            model = BookDetailModel(
                repository = repository,
                dispatcher = CoroutineExtensionTest.dispatcher,
                reducer = BookDetailReducer(),
            ),
        )
    }
}