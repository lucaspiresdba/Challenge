package br.com.lucaspires.challengexp.di

import br.com.lucaspires.challengexp.presenter.characters.CharacterFragmentPresenter
import br.com.lucaspires.challengexp.presenter.characters.CharacterFragmentPresenterImp
import br.com.lucaspires.challengexp.presenter.characters.CharacterFragmentView
import br.com.lucaspires.challengexp.presenter.details.CharacterDetailsActivityPresenter
import br.com.lucaspires.challengexp.presenter.details.CharacterDetailsActivityPresenterImp
import br.com.lucaspires.challengexp.presenter.details.CharacterDetailsActivityView
import br.com.lucaspires.challengexp.presenter.favorites.FavoriteFragmentPresenter
import br.com.lucaspires.challengexp.presenter.favorites.FavoriteFragmentPresenterImp
import br.com.lucaspires.challengexp.presenter.favorites.FavoriteFragmentView
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
        return CharacterFragmentPresenterImp(
            useCase,
            view
        )
    }

    @Provides
    fun favoritePresenter(
        useCase: CharacterUseCase,
        view: FavoriteFragmentView
    ): FavoriteFragmentPresenter {
        return FavoriteFragmentPresenterImp(
            useCase,
            view
        )
    }

    @Provides
    fun detailsPresenter(
        useCase: CharacterUseCase,
        view: CharacterDetailsActivityView
    ): CharacterDetailsActivityPresenter {
        return CharacterDetailsActivityPresenterImp(
            useCase,
            view
        )
    }
}