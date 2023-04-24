package com.begobang.presentation.ui.navigation

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.begobang.presentation.ui.screens.pokemonsList.PokemonList

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.POKEMON.route
    ) {
        pokemonsNav(navController)
    }
}

private fun NavGraphBuilder.pokemonsNav(navController: NavController) {
    navigation(
        startDestination = NavCommand.ContentType(Screens.POKEMON).route,
        route = Screens.POKEMON.route
    ) {
        composable(NavCommand.ContentType(Screens.POKEMON)) {
            PokemonList()
        }

        composable(NavCommand.ContentTypeDetail(Screens.POKEMON)) {
            it.arguments?.getString("itemId")?.let { it1 ->  Text(text = it1)}
        }
    }
}

private fun NavGraphBuilder.composable(
    navCommand: NavCommand,
    content: @Composable (NavBackStackEntry) -> Unit,
) {
    composable(
        route = navCommand.route,
        arguments = navCommand.args
    ) {
        content(it)
        Log.d("nav command", navCommand.args.toString())
    }
}