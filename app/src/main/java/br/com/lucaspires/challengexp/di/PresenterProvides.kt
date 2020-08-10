package br.com.lucaspires.challengexp.di

import br.com.lucaspires.challengexp.presenter.*
import br.com.lucaspires.domain.usecase.CharacterUseCase
import dagger.Module
import dagger.Provides

@Module
class PresenterProvides {

    @Provides
    fun characterPresenter(
        useCase: CharacterUseCase,
        view: CharacterFragmentView
    ): CharacterFragmentPresenter {
        return CharacterFragmentPresenterImp(useCase, view)
    }

    @Provides
    fun favoritePresenter(
        useCase: CharacterUseCase,
        view: FavoriteFragmentView
    ): FavoriteFragmentPresenter {
        return FavoriteFragmentPresenterImp(useCase, view)
    }

    @Provides
    fun detailsPresenter(
        useCase: CharacterUseCase,
        view: CharacterDetailsActivityView
    ): CharacterDetailsActivityPresenter {
        return CharacterDetailsActivityPresenterImp(useCase, view)
    }
}