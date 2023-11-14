package com.annguyenhoang.learningcompose.models.remote.responses

import com.annguyenhoang.learningcompose.views.screens.book_list.models.BookUIState
import java.util.UUID

data class BookResponse(
    val id: String = UUID.randomUUID().toString(),
    val name: String? = null
)

fun BookResponse.toUIState() = BookUIState(bookName = this.name ?: "Khong co ten cuon sach")