package com.begobang.presentation.screens.pokemonsList

import arrow.core.Either
import com.begobang.domain.GetPokemons
import com.begobang.domain.business.PokemonItemBusiness
import com.begobang.domain.business.PokemonsBusiness
import com.begobang.domain.failure.Failure
import com.begobang.domain.fakeRepository.FakeGetPokemonsRepository
import com.begobang.presentation.CoroutinesTestRule
import com.begobang.presentation.ui.navigation.NavigationManager
import com.begobang.presentation.ui.screens.pokemonsList.PokemonListViewModel
import com.begobang.presentation.ui.screens.pokemonsList.PokemonState
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class PokemonListViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var viewModel: PokemonListViewModel


    @Test
    fun `get pokemons - failure`() {
        //Given
        val either = Either.Left(Failure.BaseFailure(message = "test"))
        val getPokemons = GetPokemons(FakeGetPokemonsRepository(either))
        viewModel = PokemonListViewModel(getPokemons, NavigationManager())

        //When
        runBlocking { viewModel.getPokemons() }

        //Then
        assertEquals(PokemonState(error = "test"), viewModel.state.value)

    }

    @Test
    fun `get pokemons - returns data`() {
        //Given
        val pokemons = PokemonsBusiness(
            0,
            "",
            "",
            listOf(
                PokemonItemBusiness("test", "")
            ))
        val either = Either.Right(pokemons)
        val getPokemons = GetPokemons(FakeGetPokemonsRepository(either))

        viewModel = PokemonListViewModel(getPokemons, NavigationManager())

        //When
        runBlocking { viewModel.getPokemons() }

        //Then
        assertEquals(PokemonState(pokemon = pokemons), viewModel.state.value)

    }
}