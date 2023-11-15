package com.annguyenhoang.learningcompose.models.remote.reponses.user_list

import com.annguyenhoang.learningcompose.view_models.user_list.models.UserUIState
import com.google.gson.annotations.SerializedName

data class GeoResponse(
    @SerializedName("lat")
    val lat: String? = null,
    @SerializedName("lng")
    val long: String? = null
)

data class AddressResponse(
    @SerializedName("street")
    val street: String? = null,
    @SerializedName("suite")
    val suite: String? = null,
    @SerializedName("city")
    val city: String? = null,
    @SerializedName("zipcode")
    val zipcode: String? = null,
    @SerializedName("geo")
    val geo: GeoResponse? = null
)

data class CompanyResponse(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("catchPhrase")
    val catchPhrase: String? = null,
    @SerializedName("bs")
    val bs: String? = null
)

data class UserResponse(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("username")
    val username: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("address")
    val address: AddressResponse? = null,
    @SerializedName("phone")
    val phone: String? = null,
    @SerializedName("website")
    val website: String? = null,
    @SerializedName("company")
    val company: CompanyResponse? = null
)

fun UserResponse.toUIState() = UserUIState(
    userID = this.id ?: 0,
    username = this.username ?: "",
    userEmail = this.email ?: ""
)