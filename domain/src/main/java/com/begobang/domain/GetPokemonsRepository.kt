package com.begobang.domain

import com.begobang.domain.business.PokemonsBusiness

interface GetPokemonsRepository {
    suspend fun getPokemons(): PokemonsBusiness
}