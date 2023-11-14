package com.annguyenhoang.learningcompose.controllers.book_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.annguyenhoang.learningcompose.helpers.models.FetchingStatus
import com.annguyenhoang.learningcompose.models.remote.responses.BookListResponse
import com.annguyenhoang.learningcompose.models.remote.responses.toUIState
import com.annguyenhoang.learningcompose.views.screens.book_list.models.BooksUIState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

// MVC vs MVVM (android)

class BookListController {

    private val coroutineScope by lazy {
        CoroutineScope(Dispatchers.IO)
    }

    private val mutex = Mutex()

    var booksUIState by mutableStateOf(BooksUIState())

    fun fetchBookList() {
        coroutineScope.launch {
            mutex.withLock {
                booksUIState = booksUIState.copy(
                    fetchingStatus = FetchingStatus.LOADING,
                )

                try {
                    delay(3000)
                    val books = BookListResponse.mock().map { response ->
                        response.toUIState()
                    }

                    booksUIState = booksUIState.copy(
                        fetchingStatus = FetchingStatus.SUCCESS,
                        bookList = books
                    )
                } catch (e: Exception) {
                    booksUIState = booksUIState.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = "Co loi ne",
                    )
                }
            }
        }
    }

    fun cancelFetchBookList() {
        if (coroutineScope.isActive) {
            coroutineScope.cancel()
        }
    }

}