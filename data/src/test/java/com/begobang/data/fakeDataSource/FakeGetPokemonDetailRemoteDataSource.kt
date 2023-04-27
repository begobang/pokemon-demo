package com.begobang.data.fakeDataSource

import arrow.core.Either
import com.begobang.data.remoteDataSource.PokemonDetailRemoteDataSource
import com.begobang.domain.business.PokemonDetailBusiness
import com.begobang.domain.failure.Failure

class FakeGetPokemonDetailRemoteDataSource(private val response: Either<Failure, PokemonDetailBusiness?>): PokemonDetailRemoteDataSource {
    override suspend fun getPokemonDetail(name: String): Either<Failure, PokemonDetailBusiness?> {
        return response
    }
}