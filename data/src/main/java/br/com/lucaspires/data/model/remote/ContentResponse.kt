package br.com.lucaspires.data.model.remote

import com.google.gson.annotations.SerializedName

data class ContentResponse(
    @field:SerializedName("data")
    val data: ContentDataContainer? = null
)