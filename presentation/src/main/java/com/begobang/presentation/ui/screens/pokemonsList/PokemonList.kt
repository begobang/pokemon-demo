package com.begobang.presentation.ui.screens.pokemonsList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.begobang.domain.business.PokemonBusiness
import com.begobang.presentation.ui.PokemonListViewModel

@Composable
fun PokemonList(){

    //val viewModel: PokemonListViewModel = hiltViewModel()

    PokemonListScreen(loading = true)
}

@Composable
fun PokemonListScreen(loading: Boolean, pokemonList: List<PokemonBusiness> = emptyList(), error: String? = null) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (loading) {
            CircularProgressIndicator()
        }
    }

}