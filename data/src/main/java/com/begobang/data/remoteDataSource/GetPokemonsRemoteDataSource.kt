package com.begobang.data.remoteDataSource

import arrow.core.Either
import com.begobang.data.apiService.GetPokemonsApiService
import com.begobang.data.apiService.requestGenericApi
import com.begobang.data.response.toDomain
import com.begobang.domain.business.PokemonsBusiness
import com.begobang.domain.failure.Failure
import javax.inject.Inject


class GetPokemonsRemoteDataSource @Inject constructor(
    private val pokemonsApiService: GetPokemonsApiService
) {

    suspend fun getPokemons(): Either<Failure, PokemonsBusiness?> {
        return requestGenericApi(
            call = pokemonsApiService.getPokemons(),
            success = { it?.toDomain() }
        )
    }
}