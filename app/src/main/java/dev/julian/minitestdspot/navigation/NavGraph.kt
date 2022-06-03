package dev.julian.minitestdspot.navigation

import android.provider.SyncStateContract
import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.julian.minitestdspot.ui.screen.UserListScreen
import dev.julian.minitestdspot.utilities.USER_DETAILS_ARGUMENT_KEY

@Composable
fun NavGraph (navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            UserListScreen()
        }

    }
}