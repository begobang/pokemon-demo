package com.begobang.data.apiService

import com.begobang.data.response.PokemonsResponse
import retrofit2.Call
import retrofit2.http.GET

interface GetPokemonsApiService {
    @GET("/pokemon")
    fun getPokemons(): Call<PokemonsResponse>
}