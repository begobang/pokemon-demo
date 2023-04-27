package com.begobang.data.repositoryImpl

import arrow.core.Either
import com.begobang.data.localDataSource.GetPokemonDetailLocalDataSource
import com.begobang.data.localDataSource.PokemonDetailLocalDataSource
import com.begobang.data.remoteDataSource.GetPokemonDetailRemoteDataSource
import com.begobang.data.remoteDataSource.PokemonDetailRemoteDataSource
import com.begobang.domain.GetPokemonDetailRepository
import com.begobang.domain.business.PokemonDetailBusiness
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
class GetPokemonDetailRepositoryImpl @Inject constructor(
    private val pokemonDetailRemoteDataSource: PokemonDetailRemoteDataSource,
    private val pokemonDetailLocalDataSource: PokemonDetailLocalDataSource
): GetPokemonDetailRepository {
    override suspend fun getPokemonDetail(name: String): Either<Failure, PokemonDetailBusiness?> {
        return try {
            when (val response = pokemonDetailRemoteDataSource.getPokemonDetail(name)) {
                is Either.Left -> {
                    if(pokemonDetailLocalDataSource.getAll().isNotEmpty())
                        Either.Right(pokemonDetailLocalDataSource.getPokemon(name))
                    else
                        response

                }
                is Either.Right -> {
                    response.b?.let { pokemonDetailLocalDataSource.savePokemons(it) }
                    response
                }
            }
        } catch (e: Exception){
            if(pokemonDetailLocalDataSource.getAll().isNotEmpty()){
                Either.Right(pokemonDetailLocalDataSource.getPokemon(name))
            } else {
                Either.Left(Failure.BaseFailure(message = e.localizedMessage))
            }
        }
    }

}