package com.begobang.data.cache.dao

import androidx.room.*
import com.begobang.data.cache.dto.PokemonDTO

@Dao
interface PokemonDetailDao {
    @Query("SELECT * FROM pokemon")
    fun getPokemons(): List<PokemonDTO>

    @Query("SELECT * FROM pokemon WHERE name= :name")
    fun getPokemon(name: String): PokemonDTO

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemon(pokemon: PokemonDTO)

    @Delete
    fun deletePokemon(pokemon: PokemonDTO)
}