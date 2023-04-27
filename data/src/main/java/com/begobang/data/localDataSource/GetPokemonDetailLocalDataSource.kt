package com.begobang.data.localDataSource

import com.begobang.data.cache.dao.PokemonDetailDao
import com.begobang.data.cache.mapper.toDTO
import com.begobang.data.cache.mapper.toDomain
import com.begobang.domain.business.PokemonDetailBusiness
import javax.inject.Inject

/*
    This LocalDataSource establish the connection between the DataBase and the repository.
    Depending on what we need from the DataBase we will call several methods. In this class,
    we can recover all the data from the table, an specific row, or even save a new row.
 */
interface PokemonDetailLocalDataSource {
    fun getAll(): List<PokemonDetailBusiness>
    fun getPokemon(name: String): PokemonDetailBusiness
    fun savePokemons(pokemonDetailBusiness: PokemonDetailBusiness)
}
class GetPokemonDetailLocalDataSource @Inject constructor(
    private val pokemonDetailDao: PokemonDetailDao
): PokemonDetailLocalDataSource {
    override fun getAll(): List<PokemonDetailBusiness> {
        return pokemonDetailDao.getPokemons().map {
            it.toDomain()
        }
    }

    override fun getPokemon(name: String): PokemonDetailBusiness {
        return pokemonDetailDao.getPokemon(name).toDomain()
    }

    override fun savePokemons(pokemonDetailBusiness: PokemonDetailBusiness){
        pokemonDetailDao.insertPokemon(pokemonDetailBusiness.toDTO())
    }
}