package com.begobang.data.fakeDataSource

import android.util.Log
import com.begobang.data.localDataSource.PokemonDetailLocalDataSource
import com.begobang.domain.business.PokemonDetailBusiness
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
/*
    I created a fake data source so that we can test properly the repository. You can pass the response
    you want to fake in the test by using them, so that you verify each case is being controlled.

 */
class FakeGetPokemonDetailLocalDataSource(private val pokemons: List<PokemonDetailBusiness>): PokemonDetailLocalDataSource {
    private val inMemoryPokemons = MutableStateFlow(pokemons)

    override fun getAll(): List<PokemonDetailBusiness> {
        return pokemons
    }

    override fun getPokemon(name: String): PokemonDetailBusiness {
        return pokemons.find { it.name == name }!!
    }

    override fun savePokemons(pokemonDetailBusiness: PokemonDetailBusiness) {
        val mutablePokemons = mutableListOf<PokemonDetailBusiness>()
        mutablePokemons.addAll(inMemoryPokemons.value)
        mutablePokemons.add(pokemonDetailBusiness)
        inMemoryPokemons.update { mutablePokemons }
        Log.d("inMemoryPokemons", inMemoryPokemons.value.toString())
    }
}