package com.begobang.data.fakeDataSource

import arrow.core.Either
import com.begobang.data.remoteDataSource.PokemonsRemoteDataSource
import com.begobang.domain.business.PokemonsBusiness
import com.begobang.domain.failure.Failure
/*
    I created a fake data source so that we can test properly the repository. You can pass the response
    you want to fake in the test by using them, so that you verify each case is being controlled.

 */
class FakeGetPokemonsRemoteDataSource(private val response: Either<Failure, PokemonsBusiness?>): PokemonsRemoteDataSource {
    override suspend fun getPokemons(): Either<Failure, PokemonsBusiness?> {
        return response
    }

}