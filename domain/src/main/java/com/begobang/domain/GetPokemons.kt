package com.begobang.domain

import arrow.core.Either
import com.begobang.domain.business.PokemonsBusiness
import com.begobang.domain.failure.Failure
import javax.inject.Inject
import javax.inject.Singleton

/*
    UseCase classes need to be injected by Hilt so we need to add the annotation @Inject. This
    class returns a custom UseCase so that the code is not repetitive. In every use case we create
    we will make the same logic in the run function so it was better to create a base use case class.

    This class calls the repository to establish a connection between domain and data modules.
 */
@Singleton
class GetPokemons @Inject constructor(
    private val getPokemonsRepository: GetPokemonsRepository
): UseCase<PokemonsBusiness, Unit>() {
    override suspend fun run(params: Unit?): Either<Failure, PokemonsBusiness?> {
        return getPokemonsRepository.getPokemons()
    }


}