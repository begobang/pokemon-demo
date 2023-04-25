package com.begobang.data.apiService

import com.begobang.data.response.PokemonDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetPokemonDetailApiService {
    @GET("pokemon/{name}")
    fun getPokemon(
        @Path("name") name: String = "1"
    ): Call<PokemonDetailResponse>
}