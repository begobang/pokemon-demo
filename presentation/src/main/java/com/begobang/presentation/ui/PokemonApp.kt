package com.begobang.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.begobang.presentation.R
import com.begobang.presentation.ui.composables.Toolbar
import com.begobang.presentation.ui.navigation.Navigation
import com.begobang.presentation.ui.theme.PokemonTheme

/*
    PokemonApp will be the parent composable of the MainActivity. Because it is the parent, here is
    where we will need to set navController's application.

    When instantiating a viewModel with hilt, we used to used "by viewModels()" where the owners and providers
    would be automatically set, but with Compose hilt provides us with hiltViewModel() function
    which will inject our viewModel. It is very simple.

    So that we can retrieve the ScaffoldState and NavHostController we will be using the remember
    functions. Recomposition will be made when using remember values. So everytime we need to make
    a navigation, it will be possible because our NavHostController is a remember type.

 */
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


