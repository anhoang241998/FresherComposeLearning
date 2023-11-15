package com.annguyenhoang.learningcompose.view_models.user_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.annguyenhoang.learningcompose.helpers.FetchingStatus
import com.annguyenhoang.learningcompose.models.remote.reponses.user_list.toUIState
import com.annguyenhoang.learningcompose.models.remote.retrofit.RetrofitBuilder
import com.annguyenhoang.learningcompose.view_models.user_list.models.UserListUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserListViewModel : ViewModel() {

    companion object {
        private const val UNKNOWN_ERROR = "lỗi không xác định. Vui lòng thử lại sau"
    }

    private val userApi by lazy {
        RetrofitBuilder.userApi
    }

    private val _usersUIState = MutableStateFlow(UserListUIState())
    val userUIState = _usersUIState.asStateFlow()

    fun getUserApi() = viewModelScope.launch {
        try {
            _usersUIState.value = _usersUIState.value.copy(
                fetchingStatus = FetchingStatus.LOADING
            )

            val response = userApi.getUserList()

            if (response.isSuccessful) {
                response.body()?.let { usersListResponse ->
                    _usersUIState.value = _usersUIState.value.copy(
                        fetchingStatus = FetchingStatus.SUCCESS,
                        userList = usersListResponse.map { it.toUIState() }
                    )
                } ?: run {
                    _usersUIState.value = _usersUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = UNKNOWN_ERROR
                    )
                }
            } else {
                _usersUIState.value = _usersUIState.value.copy(
                    fetchingStatus = FetchingStatus.ERROR,
                    errorMsg = UNKNOWN_ERROR
                )
            }
        } catch (e: Exception) {
            _usersUIState.value = _usersUIState.value.copy(
                fetchingStatus = FetchingStatus.ERROR,
                errorMsg = e.message
            )
            e.printStackTrace()
        }
    }

}