package br.com.lucaspires.domain.di

import br.com.lucaspires.data.di.module.ServiceModule
import br.com.lucaspires.data.source.remote.MarvelAPI
import br.com.lucaspires.domain.usecase.CharacterUseCase
import br.com.lucaspires.domain.usecase.CharacterUseCaseImp
import dagger.Module
import dagger.Provides

@Module(includes = [ServiceModule::class])
class UseCaseModule {

    @Provides
    fun provideUseCase(webService: MarvelAPI): CharacterUseCase {
        return CharacterUseCaseImp(webService)
    }
}