package com.begobang.data.repositoryImpl

import arrow.core.Either
import com.begobang.data.localDataSource.GetPokemonsLocalDataSource
import com.begobang.data.localDataSource.PokemonsLocalDataSource
import com.begobang.data.remoteDataSource.GetPokemonsRemoteDataSource
import com.begobang.data.remoteDataSource.PokemonsRemoteDataSource
import com.begobang.domain.GetPokemonsRepository
import com.begobang.domain.business.PokemonsBusiness
import com.begobang.domain.failure.Failure
import javax.inject.Inject

/*
    This class makes the connection between data and domain modules.

    We can deal with 2 scenarios: The response of my request is good and we save all the data to
    our data base, or the response is not successful and we need to retrieve the previous data from
    the database, so that we can maintain the data from the last time the service worked.

    This repositoryImpl receives the interface data sources so that can be tested properly. When hilt
    injects this data sources, it will return the real data source, that is the one we need in production.
    By doing this, we can test with fakes.

 */
class GetPokemonsRepositoryImpl @Inject constructor(
    private val pokemonsRemoteDataSource: PokemonsRemoteDataSource,
    private val pokemonsLocalDataSource: PokemonsLocalDataSource
): GetPokemonsRepository {
    override suspend fun getPokemons(): Either<Failure, PokemonsBusiness?> {
        return try {
            when(val response = pokemonsRemoteDataSource.getPokemons()){
                is Either.Left -> {
                    if(pokemonsLocalDataSource.getAll().results.isNotEmpty())
                        Either.Right(pokemonsLocalDataSource.getAll())
                    else
                        response

                }
                is Either.Right -> {
                    response.b?.let { pokemonsLocalDataSource.savePokemons(it) }
                    response
                }
            }
        } catch (e: Exception) {
            if(pokemonsLocalDataSource.getAll().results.isNotEmpty()){
                Either.Right(pokemonsLocalDataSource.getAll())
            } else {
                Either.Left(Failure.BaseFailure(message = e.localizedMessage))
            }

        }


    }
}