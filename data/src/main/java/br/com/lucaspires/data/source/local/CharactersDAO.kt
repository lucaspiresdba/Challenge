package br.com.lucaspires.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.lucaspires.data.model.local.CharacterEntity
import io.reactivex.Flowable

@Dao
interface CharactersDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(characterEntity: CharacterEntity)

    @Query("SELECT * FROM characterEntity")
    fun getAllCharacters(): Flowable<List<CharacterEntity>>
}