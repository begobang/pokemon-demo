package com.begobang.presentation.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.begobang.domain.business.GameIndicesBusiness
import com.begobang.domain.business.PokemonBusiness
import com.begobang.domain.business.PokemonDetailBusiness
import com.begobang.presentation.ui.screens.pokemonDetail.Games
import com.begobang.presentation.ui.theme.PokemonTheme

@Composable
fun MovesModal(pokemon: PokemonDetailBusiness){
    Column(modifier = Modifier
        .fillMaxWidth()) {
        Toolbar(toolbarName = "Moves", showUpNavigation = false, onUpClick = {  }) {
            LazyColumn {
                items(pokemon.moves){
                    Moves(versionGroupDetails = it.versionGroupDetails, move = it.move.name)
                }

            }

        }

    }

}

@Composable
fun ModalGames(games: List<GameIndicesBusiness>){
    Column(modifier = Modifier
        .fillMaxWidth()) {
        Toolbar(toolbarName = "Games", showUpNavigation = false, onUpClick = {  }) {
            Games(items = games)

        }

    }
}


@Preview
@Composable
fun GamesPReview(){
    PokemonTheme {
        Games(items = listOf(
            GameIndicesBusiness(42, PokemonBusiness("red", "")),
            GameIndicesBusiness(42, PokemonBusiness("blue", "")),
            GameIndicesBusiness(42, PokemonBusiness("yellow", ""))
        ))
    }

}