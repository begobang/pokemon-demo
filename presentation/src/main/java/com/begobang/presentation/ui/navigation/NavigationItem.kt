package com.begobang.presentation.ui.navigation

import androidx.annotation.StringRes
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.begobang.presentation.R

/*
    I created a NavigationItem class so that we can access to some basic information of each group
    of navigations. This can be used to get the route of the actual view or to paint a different
    title to our Toolbar.
    With this class we can also know in we are in a child screen or a main screen, so that we can paint
    the back icon.

    In order to do an actual navigation we need to return the route string of each screen. Some screens
    need arguments and they will be passed through NavCommand class. We can have to types parent or child.
    The parent has a route of the corresponding screen and the child will need to specify which child is it
    and the arguments that will be provided.

    Bare in mind that this type of routes need to be added as an String. The structure of the route
    will be very similar to json requests. For example: pokemon/detail/bulbasaur
 */
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
        fun createRoute(itemId: String) =
            "${screens.route}/$subRoute/$itemId"
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