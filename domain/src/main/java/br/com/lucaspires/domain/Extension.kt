package br.com.lucaspires.domain

import br.com.lucaspires.data.model.local.CharacterEntity
import br.com.lucaspires.data.model.remote.CharacterDataContainer
import br.com.lucaspires.data.model.remote.ContentDataContainer
import br.com.lucaspires.domain.model.CharacterModel
import br.com.lucaspires.domain.model.CharactersContentModel
import br.com.lucaspires.domain.model.ContentModel
import br.com.lucaspires.domain.model.ItemContentModel

fun CharacterDataContainer.toCharactersModel(): CharactersContentModel {
    val arrayOfCharacter = arrayListOf<CharacterModel>()

    this.results?.forEach {
        arrayOfCharacter.add(
            CharacterModel(
                id = it?.id,
                name = it?.name,
                thumbnail = "${it?.thumbnail?.path}.${it?.thumbnail?.extension}",
                description = it?.description,
                isFavorite = false
            )
        )
    }

    return CharactersContentModel(
        offset = this.offset,
        limit = this.limit,
        total = this.total,
        count = this.count,
        characters = arrayOfCharacter
    )
}

fun ContentDataContainer.toContentModel(): List<ItemContentModel> {
    val listContent = arrayListOf<ItemContentModel>()

    this.results?.forEach {
        listContent.add(
            ItemContentModel(
                id = it?.id,
                title = it?.title,
                thumbnail = "${it?.thumbnail?.path}.${it?.thumbnail?.extension}"
            )
        )
    }

    return listContent
}

fun CharacterModel.toEntity(): CharacterEntity {
    return CharacterEntity(
        id = this.id,
        thumbnail = this.thumbnail,
        name = this.name,
        description = this.description
    )
}

fun List<CharacterEntity>.toCharactersModel(): List<CharacterModel> {
    val listResult = arrayListOf<CharacterModel>()
    this.forEach {
        listResult.add(CharacterModel(
            id = it.id,
            thumbnail = it.thumbnail,
            name = it.name,
            description = it.description,
            isFavorite = true
        ))
    }
    return listResult
}
