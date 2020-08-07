package br.com.lucaspires.data.model.remote

import com.google.gson.annotations.SerializedName

data class ContentItem(

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("thumbnail")
    val thumbnail: Image? = null
)