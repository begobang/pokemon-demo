package com.begobang.data.localDataSource

import com.begobang.data.cache.dao.PokemonDetailDao
import com.begobang.data.cache.mapper.toDTO
import com.begobang.data.cache.mapper.toDomain
import com.begobang.domain.business.PokemonDetailBusiness
import javax.inject.Inject

class GetPokemonDetailLocalDataSource @Inject constructor(
    private val pokemonDetailDao: PokemonDetailDao
) {
    fun getAll(): List<PokemonDetailBusiness> {
        return pokemonDetailDao.getPokemons().map {
            it.toDomain()
        }
    }

    fun getPokemon(name: String): PokemonDetailBusiness {
        return pokemonDetailDao.getPokemon(name).toDomain()
    }

    fun savePokemons(pokemonDetailBusiness: PokemonDetailBusiness){
        pokemonDetailDao.insertPokemon(pokemonDetailBusiness.toDTO())
    }
}