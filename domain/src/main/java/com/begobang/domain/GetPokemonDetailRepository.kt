package com.begobang.domain

import arrow.core.Either
import com.begobang.domain.business.PokemonDetailBusiness
import com.begobang.domain.failure.Failure

interface GetPokemonDetailRepository {
    suspend fun getPokemonDetail(name: String = "1"): Either<Failure, PokemonDetailBusiness?>
}