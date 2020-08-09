package br.com.lucaspires.challengexp.di

import android.app.Application
import android.content.Context
import br.com.lucaspires.challengexp.app.MyApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(myApp: MyApp): Context {
        return myApp
    }
}