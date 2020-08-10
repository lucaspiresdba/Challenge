package br.com.lucaspires.domain.usecase

import br.com.lucaspires.data.source.local.CharactersDAO
import br.com.lucaspires.data.source.remote.MarvelAPI
import br.com.lucaspires.domain.model.CharacterModel
import br.com.lucaspires.domain.model.CharactersContentModel
import br.com.lucaspires.domain.model.ContentModel
import br.com.lucaspires.domain.model.ItemContentModel
import br.com.lucaspires.domain.toCharactersModel
import br.com.lucaspires.domain.toContentModel
import br.com.lucaspires.domain.toEntity
import io.reactivex.Completable
import io.reactivex.Single

internal class CharacterUseCaseImp(
    private val webService: MarvelAPI,
    private val dao: CharactersDAO
) : CharacterUseCase {

    override fun getAllCharacters(offset: Int, name: String?): Single<CharactersContentModel> =
        webService
            .getAllCharacters("name", offset * 20, name)
            .map { it.data?.toCharactersModel() }

    override fun getComicsOfCharacters(characterId: Int, offset: Int): Single<List<ItemContentModel>?> =
        webService
            .getComics(characterId,"title", offset * 20)
            .map { it.data?.toContentModel() }

    override fun getSeriesOfCharacters(characterId: Int, offset: Int): Single<List<ItemContentModel>?> =
        webService
            .getSeries(characterId, "title", offset * 20)
            .map { it.data?.toContentModel() }

    override fun favoriteCharacter(characterModel: CharacterModel): Completable {
        return dao.insert(characterModel.toEntity())
    }

    override fun getFavorites(): Single<List<CharacterModel>>{
        return dao
            .getAllCharacters()
            .map { it.toCharactersModel() }
    }
}