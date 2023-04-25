package com.begobang.data.cache

import androidx.room.TypeConverter
import com.begobang.data.cache.dto.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

    @TypeConverter
    fun abilitiesToString(abilitiesDTO: List<AbilitiesDTO>): String {
        return Gson().toJson(abilitiesDTO)
    }

    @TypeConverter
    fun stringToAbilities(string: String): List<AbilitiesDTO>? {
        val listType = object : TypeToken<ArrayList<AbilitiesDTO>>() {}.type
        return Gson().fromJson(string, listType)
    }

    @TypeConverter
    fun gameIndicesToString(gameIndicesDTO: List<GameIndicesDTO>): String {
        return Gson().toJson(gameIndicesDTO)
    }

    @TypeConverter
    fun stringToGameIndices(string: String): List<GameIndicesDTO>? {
        val listType = object : TypeToken<ArrayList<GameIndicesDTO>>() {}.type
        return Gson().fromJson(string, listType)
    }

    @TypeConverter
    fun movesToString(movesDTO: List<MovesDTO>): String {
        return Gson().toJson(movesDTO)
    }

    @TypeConverter
    fun stringToMoves(string: String): List<MovesDTO>? {
        val listType = object : TypeToken<ArrayList<MovesDTO>>() {}.type
        return Gson().fromJson(string, listType)
    }

    @TypeConverter
    fun typesToString(typesDTO: List<TypesDTO>): String {
        return Gson().toJson(typesDTO)
    }

    @TypeConverter
    fun stringToTypes(string: String): List<TypesDTO>? {
        val listType = object : TypeToken<ArrayList<TypesDTO>>() {}.type
        return Gson().fromJson(string, listType)
    }

    @TypeConverter
    fun pokemonToString(pokemonsDTO: PokemonsDTO): String {
        return Gson().toJson(pokemonsDTO)
    }

    @TypeConverter
    fun stringToPokemon(string: String): PokemonsDTO? {
        return Gson().fromJson(string, PokemonsDTO::class.java)
    }


    @TypeConverter
    fun spritesToString(spritesDTO: SpritesDTO): String {
        return Gson().toJson(spritesDTO)
    }

    @TypeConverter
    fun stringToSprites(string: String): SpritesDTO? {
        return Gson().fromJson(string, SpritesDTO::class.java)
    }

    @TypeConverter
    fun typeToString(typeDTO: TypesDTO): String {
        return Gson().toJson(typeDTO)
    }

    @TypeConverter
    fun stringToType(string: String): TypesDTO? {
        return Gson().fromJson(string, TypesDTO::class.java)
    }


}