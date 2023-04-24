package com.begobang.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.begobang.presentation.R
import com.begobang.presentation.ui.composables.Toolbar
import com.begobang.presentation.ui.theme.PokemonTheme


@Composable
fun PokemonApp() {
    PokemonScreen {
        Toolbar(
            toolbarName = stringResource(id = R.string.app_name),
            showUpNavigation = false,
            showAction = true
        ) {
            Column {
                Text(text = "Hello world!")
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


