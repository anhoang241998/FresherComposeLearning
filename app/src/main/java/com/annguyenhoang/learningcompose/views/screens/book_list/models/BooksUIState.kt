package com.annguyenhoang.learningcompose.views.screens.book_list.models

import com.annguyenhoang.learningcompose.helpers.models.FetchingStatus

data class BooksUIState(
    val fetchingStatus: FetchingStatus = FetchingStatus.LOADING,
    val bookList: List<BookUIState>? = null,
    val errorMsg: String? = null
)