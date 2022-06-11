package com.sam43.currencyexchangeapp.data.local.entity

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.sam43.currencyexchangeapp.data.models.Rates
import com.sam43.currencyexchangeapp.utils.JsonParser

@ProvidedTypeConverter
class Converters(private val jsonParser: JsonParser) {
    @TypeConverter
    fun fromRatesJson(json: String): Rates? {
        return jsonParser.fromJson<Rates>(
            json,
            object : TypeToken<Rates>(){}.type
        )
    }

    @TypeConverter
    fun toRatesJson(meanings: Rates): String {
        return jsonParser.toJson(
            meanings,
            object : TypeToken<Rates>(){}.type
        ) ?: ""
    }
}