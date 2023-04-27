package com.begobang.data.fakeDataSource

import com.begobang.data.localDataSource.PokemonsLocalDataSource
import com.begobang.domain.business.PokemonsBusiness
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class FakeGetPokemonsLocalDataSource(pokemons: PokemonsBusiness): PokemonsLocalDataSource {
    private val inMemoryPokemons = MutableStateFlow(pokemons)

    override fun getAll(): PokemonsBusiness {
        return inMemoryPokemons.value
    }

    override fun savePokemons(pokemons: PokemonsBusiness) {
        inMemoryPokemons.update { pokemons }
    }
}