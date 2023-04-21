package com.begobang.data

import com.begobang.data.response.PokemonsResponse
import retrofit2.Call
import retrofit2.http.GET

interface GetPokemonApiService {
    @GET("/pokemon")
    fun getPokemons(): Call<PokemonsResponse>
}