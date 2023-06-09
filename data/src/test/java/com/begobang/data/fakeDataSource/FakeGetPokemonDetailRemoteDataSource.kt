package com.begobang.data.fakeDataSource

import arrow.core.Either
import com.begobang.data.remoteDataSource.PokemonDetailRemoteDataSource
import com.begobang.domain.business.PokemonDetailBusiness
import com.begobang.domain.failure.Failure

/*
    I created a fake data source so that we can test properly the repository. You can pass the response
    you want to fake in the test by using them, so that you verify each case is being controlled.

 */
class FakeGetPokemonDetailRemoteDataSource(private val response: Either<Failure, PokemonDetailBusiness?>): PokemonDetailRemoteDataSource {
    override suspend fun getPokemonDetail(name: String): Either<Failure, PokemonDetailBusiness?> {
        return response
    }
}