package com.begobang.data.fakeDataSource

import arrow.core.Either
import com.begobang.data.remoteDataSource.PokemonsRemoteDataSource
import com.begobang.domain.business.PokemonsBusiness
import com.begobang.domain.failure.Failure

class FakeGetPokemonsRemoteDataSource(private val response: Either<Failure, PokemonsBusiness?>): PokemonsRemoteDataSource {
    override suspend fun getPokemons(): Either<Failure, PokemonsBusiness?> {
        return response
    }

}