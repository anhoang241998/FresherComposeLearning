package com.annguyenhoang.learningcompose.models.remote.responses

data class BookListResponse(
    val list: List<BookResponse>? = null
) {

    companion object {
        fun mock() = buildList {
            repeat(20) {
                add(BookResponse())
            }
        }
    }
}
