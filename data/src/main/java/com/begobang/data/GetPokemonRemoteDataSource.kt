package com.begobang.data

import com.begobang.data.response.PokemonsResponse
import retrofit2.await
import javax.inject.Inject


class GetPokemonRemoteDataSource @Inject constructor(
    private val pokemonsApiService: GetPokemonApiService
) {

    suspend fun getPokemons(): PokemonsResponse {
        val response = pokemonsApiService.getPokemons().await()
        return response
    }
}