package br.com.lucaspires.domain.model

import java.io.Serializable

data class CharacterModel(
    val id: Int?,
    val name: String?,
    val thumbnail: String?,
    val description: String?,
    val isFavorite: Boolean?
) : Serializable