package com.begobang.presentation.ui.screens.pokemonsList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.begobang.domain.business.PokemonDetailBusiness
import com.begobang.presentation.ui.composables.EmptyState
import com.begobang.presentation.ui.theme.PokemonTheme

@Composable
fun PokemonDetail(id: String, viewModel: PokemonDetailViewModel = viewModel()) {

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
    Column {
        //if (loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .size(size = 48.dp)
                    .clickable { onRetry() }

            )

        //}

        error?.let {
            EmptyState(it) {
                onRetry()
            }
        }

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