package br.com.lucaspires.challengexp.di

import br.com.lucaspires.challengexp.iu.fragment.CharactersFragment
import br.com.lucaspires.challengexp.iu.fragment.FavoriteFragment
import br.com.lucaspires.challengexp.presenter.CharacterFragmentView
import br.com.lucaspires.challengexp.presenter.FavoriteFragmentView
import dagger.Module
import dagger.Provides

@Module
class FragmentProvider {
    @Provides
    fun favoriteProvier(): FavoriteFragmentView = FavoriteFragment()

    @Provides
    fun characterProvider(): CharacterFragmentView = CharactersFragment()
}