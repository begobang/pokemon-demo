package com.begobang.data.apiService

import com.begobang.data.response.PokemonsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetPokemonsApiService {
    @GET("pokemon")
    fun getPokemons(
        @Query("limit") limit: Int = 80
    ): Call<PokemonsResponse>
}