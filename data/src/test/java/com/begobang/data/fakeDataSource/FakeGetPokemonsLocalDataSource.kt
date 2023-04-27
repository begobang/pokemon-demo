package com.begobang.data.fakeDataSource

import com.begobang.data.localDataSource.PokemonsLocalDataSource
import com.begobang.domain.business.PokemonsBusiness
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
/*
    I created a fake data source so that we can test properly the repository. You can pass the response
    you want to fake in the test by using them, so that you verify each case is being controlled.

 */
class FakeGetPokemonsLocalDataSource(pokemons: PokemonsBusiness): PokemonsLocalDataSource {
    private val inMemoryPokemons = MutableStateFlow(pokemons)

    override fun getAll(): PokemonsBusiness {
        return inMemoryPokemons.value
    }

    override fun savePokemons(pokemons: PokemonsBusiness) {
        inMemoryPokemons.update { pokemons }
    }
}