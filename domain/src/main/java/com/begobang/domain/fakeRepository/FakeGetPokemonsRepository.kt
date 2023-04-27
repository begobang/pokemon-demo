package com.begobang.domain.fakeRepository

import arrow.core.Either
import com.begobang.domain.GetPokemonsRepository
import com.begobang.domain.business.PokemonsBusiness
import com.begobang.domain.failure.Failure
/*
    I created a fake repository so that we can test properly the use case. You can pass the response
    you want to fake in the test by using them, so that you verify each case is being controlled.

 */
class FakeGetPokemonsRepository(private val response: Either<Failure, PokemonsBusiness?>):
    GetPokemonsRepository {
    override suspend fun getPokemons(): Either<Failure, PokemonsBusiness?> {
        return response
    }
}