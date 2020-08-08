package br.com.lucaspires.challengexp.di

import br.com.lucaspires.challengexp.iu.fragment.CharactersFragment
import br.com.lucaspires.challengexp.iu.fragment.FavoriteFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector(modules = [FragmentProvider::class])
    internal abstract fun favoriteFragment(): FavoriteFragment

    @ContributesAndroidInjector(modules = [FragmentProvider::class])
    internal abstract fun characterFragment(): CharactersFragment
}