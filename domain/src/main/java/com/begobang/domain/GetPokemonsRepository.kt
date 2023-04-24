package com.begobang.domain

import arrow.core.Either
import com.begobang.domain.business.PokemonsBusiness
import com.begobang.domain.failure.Failure

interface GetPokemonsRepository {
    suspend fun getPokemons(): Either<Failure, PokemonsBusiness?>
}