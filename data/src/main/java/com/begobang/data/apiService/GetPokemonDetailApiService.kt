package com.begobang.data.apiService

import com.begobang.data.response.PokemonDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/*
    In this interface we use retrofit to make a petition to the API. In this case, we pass as
    parameters a path, because this endpoint needs the name in it, not as a query params.
 */
interface GetPokemonDetailApiService {
    @GET("pokemon/{name}")
    fun getPokemon(
        @Path("name") name: String = "1"
    ): Call<PokemonDetailResponse>
}