package br.com.lucaspires.domain.usecase

import br.com.lucaspires.data.source.local.CharactersDAO
import br.com.lucaspires.data.source.remote.MarvelAPI
import br.com.lucaspires.domain.model.CharacterModel
import br.com.lucaspires.domain.model.CharactersContentModel
import br.com.lucaspires.domain.model.ContentModel
import br.com.lucaspires.domain.toCharactersModel
import br.com.lucaspires.domain.toContentModel
import br.com.lucaspires.domain.toEntity
import br.com.lucaspires.domain.toModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

internal class CharacterUseCaseImp(
    private val webService: MarvelAPI,
    private val dao: CharactersDAO
) : CharacterUseCase {

    override fun getAllCharacters(offset: Int, name: String?): Single<CharactersContentModel> =
        webService
            .getAllCharacters("name", offset * 20, name)
            .map { it.data?.toCharactersModel() }

    override fun getComicsOfCharacters(characterId: Int, offset: Int): Single<ContentModel> =
        webService
            .getComics(characterId,"title")
            .map { it.data?.toContentModel() }

    override fun getSeriesOfCharacters(characterId: Int, offset: Int): Single<ContentModel> =
        webService
            .getSeries(characterId, "title")
            .map { it.data?.toContentModel() }

    override fun favoriteCharacter(characterModel: CharacterModel): Completable {
        return dao.insert(characterModel.toEntity())
    }

    override fun getFavorites(): Single<List<CharacterModel>>{
        return dao
            .getAllCharacters()
            .map { it.toModel() }
    }
}