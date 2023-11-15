package com.annguyenhoang.learningcompose.models.remote.retrofit

import com.annguyenhoang.learningcompose.models.remote.reponses.user_list.UserListResponse
import retrofit2.Response
import retrofit2.http.GET

interface UserApi {

    @GET("users")
    suspend fun getUserList(): Response<UserListResponse>

}