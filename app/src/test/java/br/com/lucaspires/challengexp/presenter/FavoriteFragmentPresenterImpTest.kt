package br.com.lucaspires.challengexp.presenter

import br.com.lucaspires.challengexp.presenter.favorites.FavoriteFragmentPresenter
import br.com.lucaspires.challengexp.presenter.favorites.FavoriteFragmentPresenterImp
import br.com.lucaspires.challengexp.presenter.favorites.FavoriteFragmentView
import br.com.lucaspires.domain.usecase.CharacterUseCase
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


internal class FavoriteFragmentPresenterImpTest{

    @Mock
    lateinit var view: FavoriteFragmentView

    @Mock
    lateinit var useCase: CharacterUseCase

    lateinit var presenter: FavoriteFragmentPresenter


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        presenter =
            FavoriteFragmentPresenterImp(
                useCase,
                view
            )
    }

    @After
    fun tearDown() {
        Mockito.verifyNoMoreInteractions(view, useCase)
    }

    @Test
    fun shouldBeSuccessToGetSeries() {
        Mockito.`when`(useCase.getFavorites()).thenReturn(Single.just(listOf()))

        presenter.getFavorites()

        Mockito.verify(view).sendData(listOf())
        Mockito.verify(useCase).getFavorites()
    }

    @Test
    fun shouldBeErrorToGetComics() {
        Mockito.`when`(useCase.getFavorites()).thenReturn(Single.error(Exception()))

        presenter.getFavorites()

        Mockito.verify(useCase).getFavorites()
    }
}