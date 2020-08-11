package br.com.lucaspires.domain.di

import br.com.lucaspires.data.di.module.DatabaseModule
import br.com.lucaspires.data.di.module.ServiceModule
import br.com.lucaspires.data.source.local.CharactersDAO
import br.com.lucaspires.data.source.local.ComicsDAO
import br.com.lucaspires.data.source.local.SeriesDAO
import br.com.lucaspires.data.source.remote.MarvelAPI
import br.com.lucaspires.domain.usecase.CharacterUseCase
import br.com.lucaspires.domain.usecase.CharacterUseCaseImp
import dagger.Module
import dagger.Provides

@Module(includes = [ServiceModule::class, DatabaseModule::class])
class UseCaseModule {

    @Provides
    fun provideUseCase(webService: MarvelAPI, dao: CharactersDAO, seriesDAO: SeriesDAO, comicsDAO: ComicsDAO): CharacterUseCase {
        return CharacterUseCaseImp(webService, dao, seriesDAO, comicsDAO)
    }
}