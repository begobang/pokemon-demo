package com.begobang.data.remoteDataSource

import arrow.core.Either
import com.begobang.data.apiService.GetPokemonDetailApiService
import com.begobang.data.apiService.requestGenericApi
import com.begobang.data.response.toDomain
import com.begobang.domain.business.PokemonDetailBusiness
import com.begobang.domain.failure.Failure
import javax.inject.Inject
/*
    This RemoteDataSource establish the connection between the API request and the repository.
    In this class, we can recover call refrofit service and recover the detail pokemon we need.

    I extended this RemoteDataSource to an interface, so that we can implement tests of the repository.
    The real remote data source and the fake remote data source extend this interface, so fakes an reals
    data sources can be called from repositoryImpl.
 */
interface PokemonDetailRemoteDataSource {
    suspend fun getPokemonDetail(name: String): Either<Failure, PokemonDetailBusiness?>
}
class GetPokemonDetailRemoteDataSource @Inject constructor(
    private val pokemonDetailApiService: GetPokemonDetailApiService
): PokemonDetailRemoteDataSource {

    override suspend fun getPokemonDetail(name: String): Either<Failure, PokemonDetailBusiness?> {
        return requestGenericApi(
            call = pokemonDetailApiService.getPokemon(name),
            success = { it?.toDomain() }
        )
    }
}