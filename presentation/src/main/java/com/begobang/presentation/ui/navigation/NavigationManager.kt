package com.begobang.presentation.ui.navigation

import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow

/*
    I implemented NavigationManager so that we don't make the Navigation through Navigation composable
    directly. Compose navigations are tricky, so this is a way to make a navigation more similar
    to Fragment navigations.

    First of all we will need to set the NavController when we start the app, and then
    every time we need to navigate to a different screen we will change the value of the main NavCommand,
    and the navController will make the navigation, which will be executed in the Navigation composable.

    Every screen is inside Navigation composable, so by executing the navigation with the actual NavController
    it will do the navigation.
 */
class NavigationManager {
    private var commands = MutableStateFlow<NavCommand>(NavCommand.ContentType(Screens.POKEMON))

    var navController: NavController? = null

    fun navigate(
        screens: Screens
    ) {
        commands.value = NavCommand.ContentTypeDetail(screens)
        navController?.navigate((commands.value as NavCommand.ContentTypeDetail).createRoute())
    }

    fun navigateDetail(
        screens: Screens,
        itemId: String
    ){
        commands.value = NavCommand.ContentTypeDetail(screens)
        navController?.navigate((commands.value as NavCommand.ContentTypeDetail).createRoute(itemId))
    }
}