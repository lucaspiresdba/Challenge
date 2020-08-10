package br.com.lucaspires.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.lucaspires.data.model.local.CharacterEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface CharactersDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(characterEntity: CharacterEntity): Completable

    //TODO Certo seria flowable(onNext), porém tive problemas com recyclerview - ver depois
    @Query("SELECT * FROM characterEntity ORDER BY name ASC")
    fun getAllCharacters(): Single<List<CharacterEntity>>
}