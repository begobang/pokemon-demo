package com.begobang.domain

import arrow.core.Either
import com.begobang.domain.business.FrontSpriteBusiness
import com.begobang.domain.business.OtherSpriteBusiness
import com.begobang.domain.business.PokemonBusiness
import com.begobang.domain.business.PokemonDetailBusiness
import com.begobang.domain.business.SpritesBusiness
import com.begobang.domain.failure.Failure
import com.begobang.domain.fakeRepository.FakeGetPokemonDetailRepository
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Test

/*
    In every test, we instantiate the UseCase with the Fake Repository, so that
    everytime we run the use case, it returns the data that we set on the fakes.
    So if we set a failure to the fake, it will return a failure in the use case and we can verify
    that failure case in covered properly.
 */

class GetPokemonDetailTest {

    @Test
    fun `get pokemons use case - returns failure`() {
        //Given
        val response = Either.Left(Failure.BaseFailure(message = "test"))
        val repository = FakeGetPokemonDetailRepository(response = response)
        val useCase = GetPokemonDetail(repository)

        //When
        val actual = runBlocking { useCase.run("test") }

        //Then
        TestCase.assertEquals(response, actual)
    }

    @Test
    fun `get pokemons use case - returns success`() {
        //Given
        val pokemon = PokemonDetailBusiness(
            listOf(),
            0,
            listOf(),
            0,
            0,
            emptyList(),
            "test",
            0,
            PokemonBusiness("", ""),
            SpritesBusiness("", OtherSpriteBusiness(FrontSpriteBusiness(""), FrontSpriteBusiness(""))),
            emptyList(),
            0
        )
        val response = Either.Right(pokemon)
        val repository = FakeGetPokemonDetailRepository(response = response)
        val useCase = GetPokemonDetail(repository)

        //When
        val actual = runBlocking { useCase.run("test") }

        //Then
        TestCase.assertEquals(response, actual)
    }
}