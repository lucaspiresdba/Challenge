package br.com.lucaspires.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contentItemEntity")
data class ContentItemEntity(
    @PrimaryKey
    val id: Int? = null,
    val title: String? = null,
    val thumbnail: String? = null,
    val characterId: Int? = null,
    val type: String? = null
)