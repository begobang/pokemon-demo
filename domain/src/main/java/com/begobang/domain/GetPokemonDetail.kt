package com.begobang.domain

import arrow.core.Either
import com.begobang.domain.business.PokemonDetailBusiness
import com.begobang.domain.failure.Failure
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class GetPokemonDetail @Inject constructor(
    private val getPokemonDetailRepository: GetPokemonDetailRepository
): UseCase<PokemonDetailBusiness, String>() {
    override suspend fun run(params: String?): Either<Failure, PokemonDetailBusiness?> {
        return getPokemonDetailRepository.getPokemonDetail(params ?: "1")
    }


}
