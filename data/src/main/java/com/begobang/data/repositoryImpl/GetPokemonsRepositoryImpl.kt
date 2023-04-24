package com.begobang.data.repositoryImpl

import arrow.core.Either
import com.begobang.data.remoteDataSource.GetPokemonsRemoteDataSource
import com.begobang.domain.GetPokemonsRepository
import com.begobang.domain.business.PokemonsBusiness
import com.begobang.domain.failure.Failure
import javax.inject.Inject

class GetPokemonsRepositoryImpl @Inject constructor(
    private val pokemonsRemoteDataSource: GetPokemonsRemoteDataSource
): GetPokemonsRepository {
    override suspend fun getPokemons(): Either<Failure, PokemonsBusiness?> {
        return pokemonsRemoteDataSource.getPokemons()
    }
}