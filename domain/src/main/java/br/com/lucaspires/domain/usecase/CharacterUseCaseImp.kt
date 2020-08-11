package br.com.lucaspires.domain.usecase

import br.com.lucaspires.data.source.local.CharactersDAO
import br.com.lucaspires.data.source.local.ComicsDAO
import br.com.lucaspires.data.source.local.SeriesDAO
import br.com.lucaspires.data.source.remote.MarvelAPI
import br.com.lucaspires.domain.*
import br.com.lucaspires.domain.model.CharacterModel
import br.com.lucaspires.domain.model.CharactersContentModel
import br.com.lucaspires.domain.model.ItemContentModel
import io.reactivex.Completable
import io.reactivex.Single

internal class CharacterUseCaseImp(
    private val webService: MarvelAPI,
    private val dao: CharactersDAO,
    private val seriesDAO: SeriesDAO,
    private val comicsDAO: ComicsDAO
) : CharacterUseCase {

    override fun getAllCharacters(offset: Int, name: String?): Single<CharactersContentModel> =
        webService
            .getAllCharacters("name", offset * 20, name)
            .map { it.data?.toCharactersModel() }

    override fun getComicsOfCharacters(
        characterId: Int,
        offset: Int
    ): Single<List<ItemContentModel>?> =
        webService
            .getComics(characterId, "title", offset * 20)
            .doOnSuccess { comicsDAO.insertAll(it.data?.toContentEntity(characterId, "comics")) }
            .map { it.data?.toContentModel() }
            .onErrorReturn {
                if (offset == 0) {
                    return@onErrorReturn comicsDAO.getComicsByCharacterId(characterId, "comics").blockingGet()
                        .toItemContentModel()
                } else {
                    return@onErrorReturn listOf()
                }
            }

    override fun getSeriesOfCharacters(
        characterId: Int,
        offset: Int
    ): Single<List<ItemContentModel>?> =
        webService
            .getSeries(characterId, "title", offset * 20)
            .doOnSuccess { seriesDAO.insertAll(it.data?.toContentEntity(characterId, "series")) }
            .map { it.data?.toContentModel() }
            .onErrorReturn {
                if (offset == 0) {
                    return@onErrorReturn seriesDAO.getSeriesByCharacterId(characterId, "series").blockingGet()
                        .toItemContentModel()
                } else {
                    return@onErrorReturn listOf()
                }
            }


    override fun favoriteCharacter(characterModel: CharacterModel): Completable {
        return dao.insert(characterModel.toEntity())
    }

    override fun getFavorites(): Single<List<CharacterModel>> {
        return dao
            .getAllCharacters()
            .map { it.toCharactersModel() }
    }
}