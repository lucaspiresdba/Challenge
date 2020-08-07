package br.com.lucaspires.domain.usecase

import br.com.lucaspires.domain.model.CharactersContentModel
import br.com.lucaspires.domain.model.ContentModel
import io.reactivex.Single

interface CharacterUseCase {
    fun getComicsOfCharacters(characterId: Int, offset: Int): Single<ContentModel>
    fun getSeriesOfCharacters(characterId: Int, offset: Int): Single<ContentModel>
    fun getAllCharacters(offset: Int): Single<CharactersContentModel>
}