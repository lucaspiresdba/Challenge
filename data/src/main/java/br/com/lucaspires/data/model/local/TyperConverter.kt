package br.com.lucaspires.data.model.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class TyperConverter {

    @TypeConverter
    fun itemContentToString(list: List<ContentItemEntity>): String {
        return Gson().toJson(list, object : TypeToken<List<ContentItemEntity?>?>() {}.type)
    }

    @TypeConverter
    fun stringToItemContente(json: String): List<ContentItemEntity> {
        return Gson().fromJson(json, object : TypeToken<List<ContentItemEntity?>?>() {}.type)
    }
}