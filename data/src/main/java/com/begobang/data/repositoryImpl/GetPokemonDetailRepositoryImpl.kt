package com.begobang.data.repositoryImpl

import arrow.core.Either
import com.begobang.data.remoteDataSource.GetPokemonDetailRemoteDataSource
import com.begobang.data.remoteDataSource.GetPokemonsRemoteDataSource
import com.begobang.domain.GetPokemonDetailRepository
import com.begobang.domain.GetPokemonsRepository
import com.begobang.domain.business.PokemonDetailBusiness
import com.begobang.domain.business.PokemonsBusiness
import com.begobang.domain.failure.Failure
import javax.inject.Inject

class GetPokemonDetailRepositoryImpl @Inject constructor(
    private val pokemonDetailRemoteDataSource: GetPokemonDetailRemoteDataSource
): GetPokemonDetailRepository {
    override suspend fun getPokemonDetail(name: String): Either<Failure, PokemonDetailBusiness?> {
        return pokemonDetailRemoteDataSource.getPokemonDetail(name)
    }

}