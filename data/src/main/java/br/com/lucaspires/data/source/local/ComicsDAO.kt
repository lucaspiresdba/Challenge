package br.com.lucaspires.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.lucaspires.data.model.local.ContentItemEntity
import io.reactivex.Single

@Dao
interface ComicsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(characterEntity: List<ContentItemEntity>?)

    @Query("SELECT * FROM contentItemEntity WHERE characterId =:id AND type =:type ORDER BY title ASC")
    fun getComicsByCharacterId(id: Int, type:String): Single<List<ContentItemEntity>>
}