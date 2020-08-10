package br.com.lucaspires.domain.usecase

import br.com.lucaspires.domain.model.CharacterModel
import br.com.lucaspires.domain.model.CharactersContentModel
import br.com.lucaspires.domain.model.ContentModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface CharacterUseCase {
    fun getComicsOfCharacters(characterId: Int, offset: Int): Single<ContentModel>
    fun getSeriesOfCharacters(characterId: Int, offset: Int): Single<ContentModel>
    fun getAllCharacters(offset: Int, name: String?): Single<CharactersContentModel>
    fun favoriteCharacter(characterModel: CharacterModel): Completable
    fun getFavorites(): Single<List<CharacterModel>>
}