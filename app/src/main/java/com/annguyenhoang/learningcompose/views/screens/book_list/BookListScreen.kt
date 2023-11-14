package com.annguyenhoang.learningcompose.views.screens.book_list

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.annguyenhoang.learningcompose.controllers.book_list.BookListController

@Composable
fun BookListScreen() {

    val controller = remember { BookListController() }
    val bookListUIState = controller.booksUIState

    DisposableEffect(Unit) {
        onDispose {
            controller.cancelFetchBookList()
        }
    }

    LaunchedEffect(Unit) {
        Log.d("DEBUGGG", "1")
        controller.fetchBookList()
    }

    LaunchedEffect(Unit) {
        Log.d("DEBUGGG", "2")
        controller.fetchBookList()
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        itemsIndexed(bookListUIState.bookList ?: listOf()) { index, book ->
            BookItem(
                modifier = Modifier.fillMaxWidth(),
                indexProvider = { index },
                bookNameProvider = { book.bookName }
            )
        }
    }
}

@Composable
private fun BookItem(
    modifier: Modifier = Modifier,
    indexProvider: () -> Int,
    bookNameProvider: () -> String
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "index ${indexProvider()}", fontSize = 22.sp, color = Color.LightGray)
        Text(text = "BookName: ${bookNameProvider()}", fontSize = 22.sp, color = Color.Black)
    }
}