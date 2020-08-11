package br.com.lucaspires.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.lucaspires.data.model.local.CharacterEntity
import br.com.lucaspires.data.model.local.ContentItemEntity

@Database(
    entities = [CharacterEntity::class, ContentItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun characterDao(): CharactersDAO
    abstract fun seriesDao(): SeriesDAO
    abstract fun comicsDao(): ComicsDAO
}