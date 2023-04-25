package com.begobang.data.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.begobang.data.cache.dto.PokemonsDTO

@Database(entities = [PokemonsDTO::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao

    companion object {

        // For Singleton instantiation
        @Volatile private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, AppDataBase::class.java, "Pokemons.db")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}