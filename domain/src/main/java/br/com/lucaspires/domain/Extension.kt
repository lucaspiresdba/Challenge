package br.com.lucaspires.domain

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

fun ContentDataContainer.toContentModel(): ContentModel {
    val listContent = arrayListOf<ItemContentModel>()

    this.results?.findLast {
        listContent.add(
            ItemContentModel(
                id = it?.id,
                title = it?.title,
                thumbnail = "${it?.thumbnail?.path}.${it?.thumbnail?.extension}"
            )
        )
    }

    return ContentModel(
        offset = this.offset,
        limit = this.limit,
        total = this.total,
        count = this.count,
        contentItems = listContent
    )
}