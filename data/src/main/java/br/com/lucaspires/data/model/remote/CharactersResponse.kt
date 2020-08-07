package br.com.lucaspires.data.model.remote

import com.google.gson.annotations.SerializedName

data class CharactersResponse(
    @field:SerializedName("data")
    val data: CharacterDataContainer? = null
)