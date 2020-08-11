package br.com.lucaspires.challengexp.di

import br.com.lucaspires.challengexp.iu.activity.CharacterDetailsActivity
import br.com.lucaspires.challengexp.iu.fragment.CharactersFragment
import br.com.lucaspires.challengexp.iu.fragment.FavoriteFragment
import br.com.lucaspires.challengexp.presenter.details.CharacterDetailsActivityView
import br.com.lucaspires.challengexp.presenter.characters.CharacterFragmentView
import br.com.lucaspires.challengexp.presenter.favorites.FavoriteFragmentView
import dagger.Module
import dagger.Provides

@Module(includes = [PresenterProvides::class])
class ViewsProvides {
    @Provides
    internal fun favoriteProvier(fragment: FavoriteFragment): FavoriteFragmentView = fragment

    @Provides
    internal fun characterProvider(fragment: CharactersFragment): CharacterFragmentView = fragment

    @Provides
    internal fun detailsProvides(activity: CharacterDetailsActivity): CharacterDetailsActivityView =
        activity
}