package br.com.lucaspires.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "characterEntity")
@TypeConverters(TyperConverter::class)
data class CharacterEntity(
    @PrimaryKey
    val id: Int? = null,
    val thumbnail: String? = null,
    val name: String? = null,
    val description: String? = null,
    val isFavorite: Boolean? = true
)