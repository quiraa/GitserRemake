package com.quiraadev.jetusergithub.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class SearchUserResponse(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: ListUserResponse,
    @SerializedName("total_count")
    val totalCount: Int
)
