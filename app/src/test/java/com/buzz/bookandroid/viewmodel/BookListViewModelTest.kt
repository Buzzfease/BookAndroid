package com.buzz.bookandroid.viewmodel

import com.buzz.bookandroid.MockRepository
import com.buzz.bookandroid.core.CoroutineExtensionTest
import com.buzz.bookandroid.core.InstantTaskExecutorExtension
import com.buzz.bookandroid.core.observeValues
import com.buzz.bookandroid.network.repository.NetworkRepository
import com.buzz.bookandroid.screen.booklist.model.BookListAction
import com.buzz.bookandroid.screen.booklist.model.BookListModel
import com.buzz.bookandroid.screen.booklist.model.BookListReducer
import com.buzz.bookandroid.screen.booklist.model.BookListState
import com.buzz.bookandroid.screen.booklist.model.BookListViewModel
import com.buzz.bookandroid.withBookListError
import com.google.common.truth.Truth.assertThat
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
class BookListViewModelTest {

    private val mockRepository get() = MockRepository()

    @Test
    fun `test action load data success`() {
        val viewModel = buildViewModel(
            mockRepository
        )
        val states = viewModel.state.observeValues()
        viewModel.doAction(BookListAction.LoadData)

        assertThat(states[0]).isEqualTo(BookListState.Loading)
        with(states[1] as BookListState.Data) {
            assertThat(this.dataSource).isNotEmpty()
        }
    }

    @Test
    fun `test action load data error`() {
        val viewModel = buildViewModel(
            mockRepository.withBookListError()
        )
        val states = viewModel.state.observeValues()
        viewModel.doAction(BookListAction.LoadData)

        val result = listOf(
            BookListState.Loading,
            BookListState.Error
        )

        assertThat(states).isEqualTo(result)
    }

    private fun buildViewModel(
        repository: NetworkRepository,
    ): BookListViewModel {
        return BookListViewModel(
            initialState = BookListState.Loading,
            model = BookListModel(
                repository = repository,
                dispatcher = CoroutineExtensionTest.dispatcher,
                reducer = BookListReducer(),
            ),
        )
    }
}