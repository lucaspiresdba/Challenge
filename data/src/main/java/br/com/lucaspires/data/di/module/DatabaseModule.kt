package br.com.lucaspires.data.di.module

import android.content.Context
import androidx.room.Room
import br.com.lucaspires.data.source.local.CharacterDatabase
import br.com.lucaspires.data.source.local.CharactersDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun providesCharactersDao(context: Context): CharactersDAO {
        return Room.databaseBuilder(context, CharacterDatabase::class.java, "character-db")
            .allowMainThreadQueries()
            .build()
            .characterDao()
    }
}