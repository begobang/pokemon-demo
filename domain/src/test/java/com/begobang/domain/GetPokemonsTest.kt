package com.begobang.domain

import arrow.core.Either
import com.begobang.domain.business.PokemonItemBusiness
import com.begobang.domain.business.PokemonsBusiness
import com.begobang.domain.failure.Failure
import com.begobang.domain.fakeRepository.FakeGetPokemonsRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

/*
    In every test, we instantiate the UseCase with the Fake Repository, so that
    everytime we run the use case, it returns the data that we set on the fakes.
    So if we set a failure to the fake, it will return a failure in the use case and we can verify
    that failure case in covered properly.
 */
class GetPokemonsTest {

    @Test
    fun `get pokemons use case - returns failure`() {
        //Given
        val response = Either.Left(Failure.BaseFailure(message = "test"))
        val repository = FakeGetPokemonsRepository(response = response)
        val useCase = GetPokemons(repository)

        //When
        val actual = runBlocking { useCase.run(Unit) }

        //Then
        assertEquals(response, actual)
    }

    @Test
    fun `get pokemons use case - returns success`() {
        //Given
        val pokemons = PokemonsBusiness(
            0,
            "",
            "",
            listOf(
                PokemonItemBusiness("test", "")
            ))
        val response = Either.Right(pokemons)
        val repository = FakeGetPokemonsRepository(response = response)
        val useCase = GetPokemons(repository)

        //When
        val actual = runBlocking { useCase.run(Unit) }

        //Then
        assertEquals(response, actual)
    }

}