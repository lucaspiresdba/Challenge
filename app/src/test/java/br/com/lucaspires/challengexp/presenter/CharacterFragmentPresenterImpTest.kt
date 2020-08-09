package br.com.lucaspires.challengexp.presenter

import br.com.lucaspires.data.CheckConnectionInterceptor.NoNetworkExpcetion
import br.com.lucaspires.domain.model.CharactersContentModel
import br.com.lucaspires.domain.usecase.CharacterUseCase
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


internal class CharacterFragmentPresenterImpTest {

    @Mock
    lateinit var view: CharacterFragmentView

    @Mock
    lateinit var useCase: CharacterUseCase

    lateinit var presenter: CharacterFragmentPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        presenter = CharacterFragmentPresenterImp(useCase, view)
    }

    @Test
    fun shouldBeSuccessDataRetriev() {
        Mockito.`when`(useCase.getAllCharacters(0, "blabla")).thenReturn(Single.just(getData()))

        presenter.getCharacters(0, "blabla")

        Mockito.verify(useCase).getAllCharacters(0, "blabla")
        Mockito.verify(view).sendData(getData().characters)
        Mockito.verify(view).showLoading()
        Mockito.verify(view).hideLoading()
    }

    @Test
    fun shouldBeSuccessDataRetrievOnLoadMore() {
        Mockito.`when`(useCase.getAllCharacters(1, "blabla")).thenReturn(Single.just(getData()))

        presenter.getCharacters(1, "blabla")

        Mockito.verify(useCase).getAllCharacters(1, "blabla")
        Mockito.verify(view).sendData(getData().characters)
        Mockito.verify(view).showBottomLoading()
        Mockito.verify(view).hideBottomLoading()
    }

    @Test
    fun shouldBeErrorNoConnection() {
        Mockito.`when`(useCase.getAllCharacters(0, "blabla")).thenReturn(
            Single.error(
                NoNetworkExpcetion("sem conexão")
            )
        )

        presenter.getCharacters(0, "blabla")

        Mockito.verify(useCase).getAllCharacters(0, "blabla")
        Mockito.verify(view).showLoading()
        Mockito.verify(view).hideLoading()
        Mockito.verify(view).noConnection()
    }

    @Test
    fun shouldBeError() {
        Mockito.`when`(useCase.getAllCharacters(0, "blabla")).thenReturn(
            Single.error(
                Exception()
            )
        )

        presenter.getCharacters(0, "blabla")

        Mockito.verify(useCase).getAllCharacters(0, "blabla")
        Mockito.verify(view).showLoading()
        Mockito.verify(view).hideLoading()
        Mockito.verify(view).error()
    }

    private fun getData() =
        CharactersContentModel(
            0, 0, 0, 0,
            listOf()
        )

    @After
    fun tearDown() {
        Mockito.verifyNoMoreInteractions(view, useCase)
    }
}