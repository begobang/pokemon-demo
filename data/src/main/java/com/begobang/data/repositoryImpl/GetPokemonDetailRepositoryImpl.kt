package com.begobang.data.repositoryImpl

import arrow.core.Either
import com.begobang.data.localDataSource.GetPokemonDetailLocalDataSource
import com.begobang.data.remoteDataSource.GetPokemonDetailRemoteDataSource
import com.begobang.domain.GetPokemonDetailRepository
import com.begobang.domain.business.PokemonDetailBusiness
import com.begobang.domain.failure.Failure
import javax.inject.Inject

class GetPokemonDetailRepositoryImpl @Inject constructor(
    private val pokemonDetailRemoteDataSource: GetPokemonDetailRemoteDataSource,
    private val pokemonDetailLocalDataSource: GetPokemonDetailLocalDataSource
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