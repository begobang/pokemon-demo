package com.begobang.data.remoteDataSource

import arrow.core.Either
import com.begobang.data.apiService.GetPokemonsApiService
import com.begobang.data.apiService.requestGenericApi
import com.begobang.data.response.toDomain
import com.begobang.domain.business.PokemonsBusiness
import com.begobang.domain.failure.Failure
import javax.inject.Inject

/*
    This RemoteDataSource establish the connection between the API request and the repository.
    In this class, we can recover call refrofit service and recover the pokemon list we need.

    I extended this RemoteDataSource to an interface, so that we can implement tests of the repository.
    The real remote data source and the fake remote data source extend this interface, so fakes an reals
    data sources can be called from repositoryImpl.
 */
interface PokemonsRemoteDataSource {
    suspend fun getPokemons(): Either<Failure, PokemonsBusiness?>
}

class GetPokemonsRemoteDataSource @Inject constructor(
    private val pokemonsApiService: GetPokemonsApiService
): PokemonsRemoteDataSource {

    override suspend fun getPokemons(): Either<Failure, PokemonsBusiness?> {
        return requestGenericApi(
            call = pokemonsApiService.getPokemons(),
            success = { it?.toDomain() }
        )
    }
}