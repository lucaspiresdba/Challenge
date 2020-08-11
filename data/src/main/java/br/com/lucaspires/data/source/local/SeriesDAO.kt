package br.com.lucaspires.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.lucaspires.data.model.local.ContentItemEntity
import io.reactivex.Single

@Dao
interface SeriesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(characterEntity: List<ContentItemEntity>?)

    @Query("SELECT * FROM contentItemEntity WHERE characterId =:id AND type =:type ORDER BY title ASC")
    fun getSeriesByCharacterId(id: Int, type:String): Single<List<ContentItemEntity>>
}