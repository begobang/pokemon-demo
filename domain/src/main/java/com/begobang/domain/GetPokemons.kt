package com.begobang.domain

import arrow.core.Either
import com.begobang.domain.business.PokemonsBusiness
import com.begobang.domain.failure.Failure
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPokemons @Inject constructor(
    private val getPokemonsRepository: GetPokemonsRepository
): UseCase<PokemonsBusiness, Unit>() {
    override suspend fun run(params: Unit?): Either<Failure, PokemonsBusiness?> {
        return getPokemonsRepository.getPokemons()
    }


}