package br.com.lucaspires.domain.model

data class CharactersContentModel(
    val offset: Int?,
    val limit: Int?,
    val total: Int?,
    val count: Int?,
    val characters: List<CharacterModel>?
)