package com.begobang.data.cache

import androidx.room.*
import com.begobang.data.cache.dto.PokemonsDTO

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemons")
    fun getPokemons(): List<PokemonsDTO>

    @Query("SELECT * FROM pokemons WHERE name= :name")
    fun getPokemon(name: String): PokemonsDTO

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemon(pokemon: PokemonsDTO)

    @Delete
    fun deletePokemon(pokemon: PokemonsDTO)
}