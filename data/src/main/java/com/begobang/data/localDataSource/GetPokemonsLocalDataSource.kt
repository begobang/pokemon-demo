package com.begobang.data.localDataSource

import com.begobang.data.cache.PokemonDao
import com.begobang.data.cache.dto.PokemonsDTO
import com.begobang.domain.business.PokemonItemBusiness
import com.begobang.domain.business.PokemonsBusiness
import javax.inject.Inject

class GetPokemonsLocalDataSource @Inject constructor(
    private val pokemonDao: PokemonDao
) {
    fun getAll(): PokemonsBusiness {
        return PokemonsBusiness(
            count = pokemonDao.getPokemons().size,
            next = "",
            previous = "",
            results = pokemonDao.getPokemons().map {
                PokemonItemBusiness(it.name, it.url)
            }
        )
    }

    fun savePokemons(pokemons: PokemonsBusiness){
        pokemons.results.forEach {
            pokemonDao.insertPokemon(PokemonsDTO(name = it.name, url = it.url, imageUrl = it.imageUrl))
        }
    }
}