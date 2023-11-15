package com.annguyenhoang.learningcompose.views.user_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.annguyenhoang.learningcompose.helpers.FetchingStatus
import com.annguyenhoang.learningcompose.view_models.user_list.UserListViewModel
import com.annguyenhoang.learningcompose.view_models.user_list.models.UserUIState

@Composable
fun UserListScreen(
    viewModel: UserListViewModel = viewModel()
) {
    val userUIState by viewModel.userUIState.collectAsState()
    val userList = userUIState.userList

    LaunchedEffect(Unit) {
        viewModel.getUserApi()
    }

    if (userUIState.fetchingStatus == FetchingStatus.LOADING) {
        LoadingComponent(modifier = Modifier.fillMaxSize())
    }

    if (userUIState.fetchingStatus == FetchingStatus.SUCCESS && userList != null) {
        UsersComponent(modifier = Modifier.fillMaxSize(), users = userList)
    }
}

@Composable
fun LoadingComponent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun UsersComponent(
    modifier: Modifier = Modifier,
    users: List<UserUIState>,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
    ) {
        items(users) { user ->
            UserItem(
                modifier = Modifier.fillMaxWidth(),
                user = user
            )
        }
    }
}

@Composable
fun UserItem(
    modifier: Modifier = Modifier,
    user: UserUIState
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            "${user.userID}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.LightGray
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                user.username,
                fontSize = 14.sp,
                color = Color.Black
            )

            Text(
                user.userEmail,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}