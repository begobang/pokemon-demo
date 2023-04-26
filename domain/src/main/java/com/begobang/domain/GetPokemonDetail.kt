package com.begobang.domain

import arrow.core.Either
import com.begobang.domain.business.PokemonDetailBusiness
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
class GetPokemonDetail @Inject constructor(
    private val getPokemonDetailRepository: GetPokemonDetailRepository
): UseCase<PokemonDetailBusiness, String>() {
    override suspend fun run(params: String?): Either<Failure, PokemonDetailBusiness?> {
        return getPokemonDetailRepository.getPokemonDetail(params ?: "1")
    }


}
