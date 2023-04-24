package com.begobang.presentation.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.begobang.presentation.R


@Composable
fun PokemonApp() {
    Text(text = "Hello ${stringResource(id = R.string.app_name)}!")
}


