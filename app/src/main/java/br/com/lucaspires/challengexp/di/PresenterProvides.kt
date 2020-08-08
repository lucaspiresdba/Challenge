package br.com.lucaspires.challengexp.di

import br.com.lucaspires.challengexp.presenter.CharacterFragmentPresenter
import br.com.lucaspires.challengexp.presenter.CharacterFragmentView
import br.com.lucaspires.challengexp.presenter.FavoriteFragmentPresenter
import br.com.lucaspires.challengexp.presenter.FavoriteFragmentView
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
        return CharacterFragmentPresenter(useCase, view)
    }

    @Provides
    fun favoritePresenter(
        useCase: CharacterUseCase,
        view: FavoriteFragmentView
    ): FavoriteFragmentPresenter {
        return FavoriteFragmentPresenter(useCase, view)
    }
}