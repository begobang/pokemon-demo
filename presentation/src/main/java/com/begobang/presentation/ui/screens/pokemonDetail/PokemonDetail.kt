package com.begobang.presentation.ui.screens.pokemonDetail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.begobang.domain.business.PokemonDetailBusiness
import com.begobang.presentation.ui.composables.BaseScreen
import com.begobang.presentation.ui.theme.PokemonTheme

@Composable
fun PokemonDetail(id: String, viewModel: PokemonDetailViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsState()

    viewModel.getPokemonDetail(id)

    PokemonDetailScreen(
        loading = state.loading,
        error = state.error,
        pokemon = state.pokemon
    ) {
        viewModel.getPokemonDetail(id)
    }
}

@Composable
fun PokemonDetailScreen(
    loading: Boolean = false,
    error: String? = "",
    pokemon: PokemonDetailBusiness? = null,
    onRetry: () -> Unit,
) {

    BaseScreen(loading = loading, error = error, onRetry = { onRetry() }) {
        pokemon?.let {
            Text(it.name)
            Text(it.abilities.joinToString("-"))
            Text(it.height.toString())
            Text(it.weight.toString())
        }
    }
}

@Preview
@Composable
fun DetailPreview() {
    PokemonTheme {
        PokemonDetailScreen(){

        }
    }
}