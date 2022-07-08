package br.com.zup.marvel.data.dao

import androidx.room.TypeConverter
import br.com.zup.marvel.domain.model.Director
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun converterFromDirector(value: String): Director?{
        return Gson().fromJson(value, Director::class.java)
    }

    @TypeConverter
    fun converterToJson(director: Director): String{
        return Gson().toJson(director)
    }
}