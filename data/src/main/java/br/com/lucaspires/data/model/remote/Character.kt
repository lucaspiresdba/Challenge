package br.com.lucaspires.data.model.remote

import com.google.gson.annotations.SerializedName

data class Character(

	@field:SerializedName("thumbnail")
	val thumbnail: Image? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)