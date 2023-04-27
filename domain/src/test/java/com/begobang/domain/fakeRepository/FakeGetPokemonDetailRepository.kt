package com.begobang.domain.fakeRepository

import arrow.core.Either
import com.begobang.domain.GetPokemonDetailRepository
import com.begobang.domain.business.PokemonDetailBusiness
import com.begobang.domain.failure.Failure

/*
    I created a fake repository so that we can test properly the use case. You can pass the response
    you want to fake in the test by using them, so that you verify each case is being controlled.

 */
class FakeGetPokemonDetailRepository(private val response: Either<Failure, PokemonDetailBusiness?>):
    GetPokemonDetailRepository {
    override suspend fun getPokemonDetail(name: String): Either<Failure, PokemonDetailBusiness?> {
        return response
    }
}