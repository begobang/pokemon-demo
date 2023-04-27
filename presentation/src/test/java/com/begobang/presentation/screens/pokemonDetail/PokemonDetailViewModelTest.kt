package com.begobang.presentation.screens.pokemonDetail

import arrow.core.Either
import com.begobang.domain.GetPokemonDetail
import com.begobang.domain.GetPokemons
import com.begobang.domain.business.FrontSpriteBusiness
import com.begobang.domain.business.OtherSpriteBusiness
import com.begobang.domain.business.PokemonBusiness
import com.begobang.domain.business.PokemonDetailBusiness
import com.begobang.domain.business.SpritesBusiness
import com.begobang.domain.failure.Failure
import com.begobang.domain.fakeRepository.FakeGetPokemonDetailRepository
import com.begobang.domain.fakeRepository.FakeGetPokemonsRepository
import com.begobang.presentation.CoroutinesTestRule
import com.begobang.presentation.ui.screens.pokemonDetail.PokemonDetailState
import com.begobang.presentation.ui.screens.pokemonDetail.PokemonDetailViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class PokemonDetailViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var viewModel: PokemonDetailViewModel

    @Test
    fun `get pokemon detail - failure`() {
        //Given
        val either = Either.Left(Failure.BaseFailure(message = "Test"))
        val getPokemonDetail = GetPokemonDetail(FakeGetPokemonDetailRepository(either))
        viewModel = PokemonDetailViewModel(getPokemonDetail)

        //When
        runBlocking { viewModel.getPokemonDetail("1") }

        //Then
        assertEquals(PokemonDetailState(error = "Test"), viewModel.state.value)
    }

    @Test
    fun `get pokemon detail - returns data`() {
        //Given
        val pokemon = PokemonDetailBusiness(
            listOf(),
            0,
            listOf(),
            0,
            0,
            emptyList(),
            "test",
            0,
            PokemonBusiness("", ""),
            SpritesBusiness("", OtherSpriteBusiness(FrontSpriteBusiness(""), FrontSpriteBusiness(""))),
            emptyList(),
            0
        )
        val either = Either.Right(pokemon)

        val getPokemonDetail = GetPokemonDetail(FakeGetPokemonDetailRepository(either))
        viewModel = PokemonDetailViewModel(getPokemonDetail)

        //When
        runBlocking { viewModel.getPokemonDetail("1") }

        //Then
        assertEquals(PokemonDetailState(pokemon = pokemon), viewModel.state.value)
    }
}