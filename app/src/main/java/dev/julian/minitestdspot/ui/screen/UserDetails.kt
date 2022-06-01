package dev.julian.minitestdspot.ui.screen

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.julian.minitestdspot.viewmodels.UserViewModel

@Composable
fun UserDetails(
    userId: String,
    navController: NavController,
    viewModel: UserViewModel = hiltViewModel(),
) {
    Scaffold() {

    }
}