package br.com.lucaspires.domain.usecase

import br.com.lucaspires.data.model.local.CharacterEntity
import br.com.lucaspires.data.model.local.ContentItemEntity
import br.com.lucaspires.data.model.remote.*
import br.com.lucaspires.data.source.local.CharactersDAO
import br.com.lucaspires.data.source.local.ComicsDAO
import br.com.lucaspires.data.source.local.SeriesDAO
import br.com.lucaspires.data.source.remote.MarvelAPI
import br.com.lucaspires.domain.toContentEntity
import io.reactivex.Single
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class CharacterUseCaseImpTest {

    @Mock
    lateinit var marvelAPI: MarvelAPI

    @Mock
    lateinit var dao: CharactersDAO

    @Mock
    lateinit var seriesDAO: SeriesDAO

    @Mock
    lateinit var comicsDAO: ComicsDAO

    lateinit var useCase: CharacterUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }

        useCase = CharacterUseCaseImp(marvelAPI, dao, seriesDAO, comicsDAO)
    }

    @After
    fun after() {
        Mockito.verifyNoMoreInteractions(marvelAPI, dao, seriesDAO, comicsDAO)
    }

    @Test
    fun shoudlBeSuccessWhenGetCharacters() {
        Mockito.`when`(marvelAPI.getAllCharacters("name", 0, "teste"))
            .thenReturn(Single.just(charactersData()))

        val request = useCase.getAllCharacters(0, "teste").blockingGet()

        assertEquals(request.characters!!.size, 2)
        assertEquals(request.characters!![0].name, "teste1")
        assertEquals(request.characters!![1].name, "test2")

        Mockito.verify(marvelAPI).getAllCharacters("name", 0, "teste")
    }

    @Test
    fun shouldBeSuccessWhenGetComics() {
        Mockito.`when`(marvelAPI.getComics(1, "title", 0)).thenReturn(Single.just(comicsData()))

        val request = useCase.getComicsOfCharacters(1, 0).blockingGet()

        assertEquals(request!!.size, 3)
        assertEquals(request[2].title, "comic3")

        Mockito.verify(marvelAPI).getComics(1, "title", 0)
        Mockito.verify(comicsDAO).insertAll(comicsData().data!!.toContentEntity(1, "comics"))
    }

    @Test
    fun shouldBeErrorWhenGetComicsAndGetFromDB() {
        Mockito.`when`(marvelAPI.getComics(1, "title", 0)).thenReturn(Single.error(Exception()))
        Mockito.`when`(comicsDAO.getComicsByCharacterId(1, "comics"))
            .thenReturn(Single.just(comicsDataEntity()))

        val request = useCase.getComicsOfCharacters(1, 0).blockingGet()

        assertEquals(request!!.size, 2)
        assertEquals(request[1].title, "fromDB2")

        Mockito.verify(marvelAPI).getComics(1, "title", 0)
        Mockito.verify(comicsDAO).getComicsByCharacterId(1, "comics")
    }

    @Test
    fun shouldBeSuccessWhenGetSeries() {
        Mockito.`when`(marvelAPI.getSeries(1, "title", 0)).thenReturn(Single.just(comicsData()))

        val request = useCase.getSeriesOfCharacters(1, 0).blockingGet()

        assertEquals(request!!.size, 3)
        assertEquals(request[2].title, "comic3")

        Mockito.verify(marvelAPI).getSeries(1, "title", 0)
        Mockito.verify(seriesDAO).insertAll(comicsData().data!!.toContentEntity(1, "series"))
    }

    @Test
    fun shouldBeErrorWhenGetSeriessAndGetFromDB() {
        Mockito.`when`(marvelAPI.getSeries(1, "title", 0)).thenReturn(Single.error(Exception()))
        Mockito.`when`(seriesDAO.getSeriesByCharacterId(1, "series"))
            .thenReturn(Single.just(comicsDataEntity()))

        val request = useCase.getSeriesOfCharacters(1, 0).blockingGet()

        assertEquals(request!!.size, 2)
        assertEquals(request[1].title, "fromDB2")

        Mockito.verify(marvelAPI).getSeries(1, "title", 0)
        Mockito.verify(seriesDAO).getSeriesByCharacterId(1, "series")
    }

    @Test
    fun shouldBeSuccessWhenGetFavorites() {
        Mockito.`when`(dao.getAllCharacters()).thenReturn(Single.just(getFavorites()))

        val request = useCase.getFavorites().blockingGet()

        assertEquals(request.size, 2)
        assertEquals(request[1].name, "fav2")

        Mockito.verify(dao).getAllCharacters()
    }


    private fun charactersData(): CharactersResponse {
        return CharactersResponse(
            CharacterDataContainer(
                results = listOf(
                    Character(Image(), "teste1", "description test", 0),
                    Character(Image(), "test2", "description test", 1)
                )
            )
        )
    }

    private fun comicsData() = ContentResponse(
        ContentDataContainer(
            results = listOf(
                ContentItem("comic1", 0, Image()),
                ContentItem("comic2", 1, Image()),
                ContentItem("comic3", 2, Image())
            )
        )
    )

    private fun comicsDataEntity() = listOf(
        ContentItemEntity(1, "fromDB"),
        ContentItemEntity(2, "fromDB2")
    )

    private fun getFavorites() = listOf(
        CharacterEntity(0, name = "fav1"),
        CharacterEntity(1, name = "fav2")
    )
}