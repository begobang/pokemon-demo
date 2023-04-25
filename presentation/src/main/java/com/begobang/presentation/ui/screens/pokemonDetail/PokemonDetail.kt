package com.begobang.presentation.ui.screens.pokemonDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.begobang.domain.business.PokemonDetailBusiness
import com.begobang.presentation.R
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
            Column {
                LazyColumn(
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 24.dp, top = 24.dp)
                ) {
                    item {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(it.sprites.other.home.frontDefault)
                                .crossfade(500)
                                .build(),
                            contentDescription = it.name,
                            contentScale = ContentScale.FillHeight,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.35f)
                                .clip(shape = MaterialTheme.shapes.medium)
                                .background(MaterialTheme.colors.primaryVariant),
                            placeholder = painterResource(R.drawable.ic_pokemon),
                            error = painterResource(R.drawable.ic_pokemon)
                        )
                    }
                    item { Text(it.name) }
                    item { Text(it.abilities.joinToString("-")) }
                    item { Text(it.height.toString()) }
                    item { Text(it.weight.toString()) }

                }



            }
        }
    }
}