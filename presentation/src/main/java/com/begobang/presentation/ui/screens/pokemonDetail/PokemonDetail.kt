package com.begobang.presentation.ui.screens.pokemonDetail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.begobang.domain.business.GameIndicesBusiness
import com.begobang.domain.business.PokemonDetailBusiness
import com.begobang.presentation.ui.composables.Abilities
import com.begobang.presentation.ui.composables.BaseScreen
import com.begobang.presentation.ui.composables.Header
import com.begobang.presentation.ui.composables.ModalGames
import com.begobang.presentation.ui.composables.MovesModal
import com.begobang.presentation.ui.composables.ProfileImage
import com.begobang.presentation.ui.composables.Types
import kotlinx.coroutines.launch
import java.util.Locale


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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PokemonDetailScreen(
    loading: Boolean = false,
    error: String? = null,
    pokemon: PokemonDetailBusiness? = null,
    onRetry: () -> Unit = {},
) {

    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    var isGames by rememberSaveable { mutableStateOf(false) }

    BaseScreen(loading = loading, error = error, onRetry = { onRetry() }) {
        pokemon?.let {
            ModalBottomSheetLayout(
                sheetContent = { if(isGames) ModalGames(games = it.gameIndices) else MovesModal(pokemon = it) },
                sheetElevation = 8.dp,
                sheetState = state,
                sheetShape = MaterialTheme.shapes.small
            ) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 24.dp)) {
                    Header(item = it) {
                        ProfileImage(url = it.sprites.other.home.frontDefault, name = it.name)
                    }

                    Row(modifier = Modifier.padding(horizontal = 40.dp), horizontalArrangement = Arrangement.Center) {
                        Abilities(abilities = it.abilities)
                        Types(types = it.types)
                    }

                    Row(modifier = Modifier.fillMaxWidth().padding(all = 20.dp), horizontalArrangement = Arrangement.Center) {
                        Button(
                            onClick = {
                                isGames = true
                                scope.launch { state.show() }
                            },
                            modifier = Modifier.padding(end = 20.dp)
                        ) {
                            Text(text = "GAMES", style = MaterialTheme.typography.h1)

                        }


                        OutlinedButton(
                            onClick = {
                                isGames = false
                                scope.launch { state.show() }

                            },
                            border = BorderStroke(2.dp, color = MaterialTheme.colors.primary)
                        ) {
                            Text(text = "MOVES", style = MaterialTheme.typography.h2)

                        }
                    }









                }

            }



        }
    }
}



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Games(items: List<GameIndicesBusiness>){
    Column {
        LazyVerticalGrid(columns = GridCells.Fixed(2)){
            items(items){
                Chip(
                    onClick = { },
                    modifier = Modifier.padding(end = 10.dp),
                    colors = ChipDefaults.chipColors(backgroundColor = MaterialTheme.colors.surface)
                ) {
                    Row {
                        Icon(
                            imageVector = Icons.Default.Games,
                            contentDescription = it.version.name,
                            tint = MaterialTheme.colors.primaryVariant,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                        Text(
                            text = it.version.name.replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(
                                    Locale.getDefault()
                                ) else it.toString()
                            },
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                            color = MaterialTheme.colors.secondary
                        )
                    }

                }
            }
        }
        LazyColumn(contentPadding = PaddingValues(start = 12.dp)) {

        }
    }

}


