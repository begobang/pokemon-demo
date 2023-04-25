package com.begobang.data.cache.dao

import androidx.room.*
import com.begobang.data.cache.dto.PokemonsDTO

/*
    This is a Dao interface which will make several petitions to the DataBase like in SQL.
    Insert and Delete annotations don't need to have the SQL query. They automatically makes the
    petition that we desire.

    Room in the background will implement a Dao implementation where they make all the logic needed
    to make this calls over our DB
 */
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