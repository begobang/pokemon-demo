package com.begobang.data.apiService

import com.begobang.data.response.PokemonsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/*
    In this interface we use retrofit to make a petition to the API. In this case, we pass as
    parameters a query, which will form the request as "endpoint/pokemon?limit=80"
 */
interface GetPokemonsApiService {
    @GET("pokemon")
    fun getPokemons(
        @Query("limit") limit: Int = 80
    ): Call<PokemonsResponse>
}