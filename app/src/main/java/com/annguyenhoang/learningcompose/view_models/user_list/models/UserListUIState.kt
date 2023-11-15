package com.annguyenhoang.learningcompose.view_models.user_list.models

import com.annguyenhoang.learningcompose.helpers.FetchingStatus

data class UserListUIState(
    val fetchingStatus: FetchingStatus = FetchingStatus.LOADING,
    val userList: List<UserUIState>? = null,
    val errorMsg: String? = null
)