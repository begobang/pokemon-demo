package com.begobang.data.repositoryImpl

import arrow.core.Either
import com.begobang.data.fakeDataSource.FakeGetPokemonsLocalDataSource
import com.begobang.data.fakeDataSource.FakeGetPokemonsRemoteDataSource
import com.begobang.domain.business.PokemonItemBusiness
import com.begobang.domain.business.PokemonsBusiness
import com.begobang.domain.failure.Failure
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
/*
    In every test, we instantiate the RepositoryImpl with the Fake Data Sources, so that
    everytime we call the repository functions, it returns the data that we set on the fakes.
    So if we set a failure to the fake, it will return a failure in the repository and we can verify
    that failure case in covered properly.
 */

class GetPokemonsRepositoryImplTest {

    @Test
    fun `get pokemons - returns failure and db is empty`() {
        //Given
        val either = Either.Left(Failure.BaseFailure(message = "error"))
        val remoteDataSource = FakeGetPokemonsRemoteDataSource(either)
        val localDataSource = FakeGetPokemonsLocalDataSource(PokemonsBusiness(0, "", "", emptyList()))
        val repository = GetPokemonsRepositoryImpl(remoteDataSource, localDataSource)

        //When
        val response = runBlocking { repository.getPokemons() }

        //Then
        assertEquals(either, response)
    }

    @Test
    fun `get pokemons - returns failure and db is not empty`() {
        //Given
        val either = Either.Left(Failure.BaseFailure(message = "error"))
        val remoteDataSource = FakeGetPokemonsRemoteDataSource(either)
        val localPokemons = PokemonsBusiness(
            0,
            "",
            "",
            listOf(PokemonItemBusiness("test", "")
        ))
        val localDataSource = FakeGetPokemonsLocalDataSource(localPokemons)
        val repository = GetPokemonsRepositoryImpl(remoteDataSource, localDataSource)

        //When
        val response = runBlocking { repository.getPokemons() }

        //Then
        assertEquals(Either.Right(localPokemons), response)
    }

    @Test
    fun `get pokemons - returns success`() {
        //Given
        val data = PokemonsBusiness(
            0,
            "",
            "",
            listOf(PokemonItemBusiness("test", "")
            ))
        val either = Either.Right(data)
        val remoteDataSource = FakeGetPokemonsRemoteDataSource(either)
        val localDataSource = FakeGetPokemonsLocalDataSource(PokemonsBusiness(0, "", "", emptyList()))
        val repository = GetPokemonsRepositoryImpl(remoteDataSource, localDataSource)

        //When
        val response = runBlocking { repository.getPokemons() }

        //Then
        assertEquals(either, response)
        assertEquals(data, localDataSource.getAll())
    }


}