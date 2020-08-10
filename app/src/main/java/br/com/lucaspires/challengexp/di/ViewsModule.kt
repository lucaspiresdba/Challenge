package br.com.lucaspires.challengexp.di

import br.com.lucaspires.challengexp.iu.activity.CharacterDetailsActivity
import br.com.lucaspires.challengexp.iu.fragment.CharactersFragment
import br.com.lucaspires.challengexp.iu.fragment.FavoriteFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ViewsModule {

    @ContributesAndroidInjector(modules = [ViewsProvides::class])
    internal abstract fun favoriteFragment(): FavoriteFragment

    @ContributesAndroidInjector(modules = [ViewsProvides::class])
    internal abstract fun characterFragment(): CharactersFragment

    @ContributesAndroidInjector(modules = [ViewsProvides::class])
    internal abstract fun detailsActivit(): CharacterDetailsActivity
}