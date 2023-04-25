package com.begobang.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.begobang.presentation.R
import com.begobang.presentation.ui.composables.AppBarIcon
import com.begobang.presentation.ui.composables.Toolbar
import com.begobang.presentation.ui.navigation.Navigation
import com.begobang.presentation.ui.screens.pokemonsList.PokemonDetail
import com.begobang.presentation.ui.screens.pokemonsList.PokemonList
import com.begobang.presentation.ui.theme.PokemonTheme
import kotlin.random.Random


@Composable
fun PokemonApp(appState: PokemonAppState = rememberPokemonAppState(), viewModel: PokemonAppViewModel = hiltViewModel()) {

    viewModel.setNavController(appState.navController)

    PokemonScreen {
        Toolbar(
            toolbarName = stringResource(id = if(appState.showUpNavigation) R.string.detail else R.string.app_name),
            showUpNavigation = appState.showUpNavigation,
            onUpClick = { appState.onUpClick() }
        ) {
            Column {
                Navigation(appState.navController)
            }
        }
    }
}

@Composable
fun PokemonScreen(content: @Composable () -> Unit){
    PokemonTheme {
       Surface(color = MaterialTheme.colors.background) {
          content() 
       } 
    }
}


