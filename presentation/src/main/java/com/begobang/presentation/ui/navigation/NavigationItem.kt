package com.begobang.presentation.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.begobang.presentation.R

enum class NavigationItem(
    val navCommand: NavCommand,
    @StringRes val title: Int
){
    POKEMONS(NavCommand.ContentType(Screens.POKEMON), R.string.app_name)
}

sealed class NavCommand(
    internal val screens: Screens,
    internal val subRoute: String = Screens.POKEMON.route,
    private val navArgs: List<NavArgs> = emptyList()
){
    class ContentType(screen: Screens): NavCommand(screens = screen)

    class ContentTypeDetail(screens: Screens, subRoute: String = "detail") :
        NavCommand(screens = screens, subRoute = subRoute, listOf(NavArgs.ItemId)) {
        fun createRoute(itemId: String) = "${screens.route}/$subRoute/$itemId"
        fun createRoute(): String = "${screens.route}/$subRoute"
    }

    val route = run {
        val argValues = navArgs.map { "{${it.key}}" }
        listOf(screens.route)
            .plus(subRoute)
            .plus(argValues)
            .joinToString("/")
    }

    val args = navArgs.map {
        navArgument(it.key) { type = it.value }
    }

}

enum class NavArgs(val key: String, val value: NavType<*>){
    ItemId("itemId", NavType.StringType)
}