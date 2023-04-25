package com.begobang.presentation.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.begobang.presentation.ui.screens.pokemonDetail.PokemonDetail
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
            it.arguments?.getString("itemId")?.let { itemId ->  PokemonDetail(id = itemId) }
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