package com.begobang.data.remoteDataSource

import com.begobang.data.apiService.GetPokemonsApiService
import com.begobang.data.response.PokemonsResponse
import retrofit2.await
import javax.inject.Inject


class GetPokemonsRemoteDataSource @Inject constructor(
    private val pokemonsApiService: GetPokemonsApiService
) {

    suspend fun getPokemons(): PokemonsResponse {
        val response = pokemonsApiService.getPokemons().await()
        return response
    }
}