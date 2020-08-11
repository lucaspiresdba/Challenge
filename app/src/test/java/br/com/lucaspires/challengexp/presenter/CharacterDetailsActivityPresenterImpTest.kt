package br.com.lucaspires.challengexp.presenter

import br.com.lucaspires.domain.model.CharacterModel
import br.com.lucaspires.domain.model.CharactersContentModel
import br.com.lucaspires.domain.usecase.CharacterUseCase
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


internal class CharacterDetailsActivityPresenterImpTest {

    @Mock
    lateinit var view: CharacterDetailsActivityView

    @Mock
    lateinit var useCase: CharacterUseCase

    lateinit var presenter: CharacterDetailsActivityPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        presenter = CharacterDetailsActivityPresenterImp(useCase, view)
    }

    @After
    fun tearDown() {
        Mockito.verifyNoMoreInteractions(view, useCase)
    }

    @Test
    fun shouldBeSuccessToGetComics() {
        Mockito.`when`(useCase.getComicsOfCharacters(0, 0)).thenReturn(Single.just(listOf()))

        presenter.getComics(0, 0)

        Mockito.verify(view).populateComics(listOf())
        Mockito.verify(useCase).getComicsOfCharacters(0, 0)
    }

    @Test
    fun shouldBeSuccessToGetSeries() {
        Mockito.`when`(useCase.getSeriesOfCharacters(0, 0)).thenReturn(Single.just(listOf()))

        presenter.getSeries(0, 0)

        Mockito.verify(view).populateSeries(listOf())
        Mockito.verify(useCase).getSeriesOfCharacters(0, 0)
    }

    @Test
    fun shouldBeErrorToGetComics() {
        Mockito.`when`(useCase.getComicsOfCharacters(0, 0)).thenReturn(Single.error(Exception()))

        presenter.getComics(0, 0)


        Mockito.verify(useCase).getComicsOfCharacters(0, 0)
    }

    @Test
    fun shouldBeErrorToGetSeries() {
        Mockito.`when`(useCase.getSeriesOfCharacters(0, 0)).thenReturn(Single.error(Exception()))

        presenter.getSeries(0, 0)

        Mockito.verify(useCase).getSeriesOfCharacters(0, 0)
    }

    @Test
    fun shouldBeSuccessToSaveFav() {
        Mockito.`when`(useCase.favoriteCharacter(getData().characters!![0]))
            .thenReturn(Completable.complete())

        presenter.saveFav(getData().characters!![0])

        Mockito.verify(useCase).favoriteCharacter(getData().characters!![0])
        Mockito.verify(view).saveSuccess()
    }

    @Test
    fun shouldBeErrorToSaveFav() {
        Mockito.`when`(useCase.favoriteCharacter(getData().characters!![0]))
            .thenReturn(Completable.error(Exception()))

        presenter.saveFav(getData().characters!![0])

        Mockito.verify(useCase).favoriteCharacter(getData().characters!![0])
        Mockito.verify(view).saveError()
    }

    private fun getData() =
        CharactersContentModel(
            0, 0, 0, 0,
            listOf(CharacterModel(1, "", "", "", true))
        )

}