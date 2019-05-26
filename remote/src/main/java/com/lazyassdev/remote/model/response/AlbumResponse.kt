package com.lazyassdev.remote.model.response

import com.google.gson.annotations.SerializedName

data class AlbumResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("userId") val userId: Long,
    @SerializedName("title") val title: String
)