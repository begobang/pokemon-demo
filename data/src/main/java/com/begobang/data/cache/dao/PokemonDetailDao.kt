package com.begobang.data.cache.dao

import androidx.room.*
import com.begobang.data.cache.dto.PokemonDTO

/*
    This is a Dao interface which will make several petitions to the DataBase like in SQL.
    Insert and Delete annotations don't need to have the SQL query. They automatically makes the
    petition that we desire.

    Room in the background will implement a Dao implementation where they make all the logic needed
    to make this calls over our DB
 */
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