package br.com.lucaspires.data.model.remote

import com.google.gson.annotations.SerializedName

data class Image(

    @field:SerializedName("path")
    val path: String? = null,

    @field:SerializedName("extension")
    val extension: String? = null
)