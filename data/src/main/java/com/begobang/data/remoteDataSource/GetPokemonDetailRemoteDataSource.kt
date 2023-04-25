package com.begobang.data.remoteDataSource

import arrow.core.Either
import com.begobang.data.apiService.GetPokemonDetailApiService
import com.begobang.data.apiService.requestGenericApi
import com.begobang.data.response.toDomain
import com.begobang.domain.business.PokemonDetailBusiness
import com.begobang.domain.failure.Failure
import javax.inject.Inject

class GetPokemonDetailRemoteDataSource @Inject constructor(
    private val pokemonDetailApiService: GetPokemonDetailApiService
) {

    suspend fun getPokemonDetail(name: String = "1"): Either<Failure, PokemonDetailBusiness?> {
        return requestGenericApi(
            call = pokemonDetailApiService.getPokemon(name),
            success = { it?.toDomain() }
        )
    }
}