package br.com.lucaspires.data.di.module

import android.content.Context
import androidx.room.Room
import br.com.lucaspires.data.source.local.CharacterDatabase
import br.com.lucaspires.data.source.local.CharactersDAO
import br.com.lucaspires.data.source.local.ComicsDAO
import br.com.lucaspires.data.source.local.SeriesDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun providesDatabase(context: Context): CharacterDatabase {
        return Room.databaseBuilder(context, CharacterDatabase::class.java, "character-db")
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun providesCharactersDao(characterDatabase: CharacterDatabase): CharactersDAO {
        return characterDatabase
            .characterDao()
    }

    @Singleton
    @Provides
    fun providesSeriesDao(characterDatabase: CharacterDatabase): SeriesDAO {
        return characterDatabase
            .seriesDao()
    }

    @Singleton
    @Provides
    fun providesComicsDao(characterDatabase: CharacterDatabase): ComicsDAO {
        return characterDatabase
            .comicsDao()
    }
}