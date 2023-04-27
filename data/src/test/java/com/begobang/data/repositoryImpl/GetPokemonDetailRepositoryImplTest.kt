package com.begobang.data.repositoryImpl

import arrow.core.Either
import com.begobang.data.fakeDataSource.FakeGetPokemonDetailLocalDataSource
import com.begobang.data.fakeDataSource.FakeGetPokemonDetailRemoteDataSource
import com.begobang.domain.business.FrontSpriteBusiness
import com.begobang.domain.business.OtherSpriteBusiness
import com.begobang.domain.business.PokemonBusiness
import com.begobang.domain.business.PokemonDetailBusiness
import com.begobang.domain.business.SpritesBusiness
import com.begobang.domain.failure.Failure
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Test

/*
    In every test, we instantiate the RepositoryImpl with the Fake Data Sources, so that
    everytime we call the repository functions, it returns the data that we set on the fakes.
    So if we set a failure to the fake, it will return a failure in the repository and we can verify
    that failure case in covered properly.
 */

class GetPokemonDetailRepositoryImplTest {

    private val data = listOf(
        PokemonDetailBusiness(
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
    )

    @Test
    fun `get pokemon detail - returns failure and db is empty`() {
        //Given
        val either = Either.Left(Failure.BaseFailure(message = "error"))
        val remoteDataSource = FakeGetPokemonDetailRemoteDataSource(either)
        val localDataSource = FakeGetPokemonDetailLocalDataSource(emptyList())
        val repository = GetPokemonDetailRepositoryImpl(remoteDataSource, localDataSource)

        //When
        val response = runBlocking { repository.getPokemonDetail("test") }

        //Then
        TestCase.assertEquals(either, response)
    }

    @Test
    fun `get pokemon detail - returns failure and db is not empty`() {
        //Given
        val either = Either.Left(Failure.BaseFailure(message = "error"))
        val remoteDataSource = FakeGetPokemonDetailRemoteDataSource(either)
        val localDataSource = FakeGetPokemonDetailLocalDataSource(data)
        val repository = GetPokemonDetailRepositoryImpl(remoteDataSource, localDataSource)

        //When
        val response = runBlocking { repository.getPokemonDetail("test") }

        //Then
        val expected = Either.Right(data.find { it.name == "test" })
        TestCase.assertEquals(expected, response)
    }

    @Test
    fun `get pokemon detail - returns success`() {
        //Given
        val either = Either.Right(data[0])
        val remoteDataSource = FakeGetPokemonDetailRemoteDataSource(either)
        val localDataSource = FakeGetPokemonDetailLocalDataSource(data)
        val repository = GetPokemonDetailRepositoryImpl(remoteDataSource, localDataSource)

        //When
        val response = runBlocking { repository.getPokemonDetail("test") }
        val result = localDataSource.getAll()

        //Then
        TestCase.assertEquals(either, response)
        TestCase.assertEquals(data, result)
    }


}