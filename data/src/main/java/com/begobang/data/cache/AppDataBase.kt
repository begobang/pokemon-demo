package com.begobang.data.cache

import android.content.Context
import androidx.room.*
import com.begobang.data.cache.dao.PokemonDao
import com.begobang.data.cache.dao.PokemonDetailDao
import com.begobang.data.cache.dto.PokemonDTO
import com.begobang.data.cache.dto.PokemonsDTO

@Database(entities = [PokemonsDTO::class, PokemonDTO::class], version = 1)
@TypeConverters(Converter::class)
abstract class AppDataBase: RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    abstract fun pokemonDetailDao(): PokemonDetailDao

    companion object {

        // For Singleton instantiation
        @Volatile private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, AppDataBase::class.java, "Pokemon-Api.db")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}