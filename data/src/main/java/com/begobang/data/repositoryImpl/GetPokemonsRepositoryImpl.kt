package com.begobang.data.repositoryImpl

import com.begobang.data.remoteDataSource.GetPokemonsRemoteDataSource
import com.begobang.data.response.toDomain
import com.begobang.domain.GetPokemonsRepository
import com.begobang.domain.business.PokemonsBusiness
import javax.inject.Inject

class GetPokemonsRepositoryImpl @Inject constructor(
    private val pokemonsRemoteDataSource: GetPokemonsRemoteDataSource
): GetPokemonsRepository {
    override suspend fun getPokemons(): PokemonsBusiness {
        val response = pokemonsRemoteDataSource.getPokemons()
        return response.toDomain()
    }
}