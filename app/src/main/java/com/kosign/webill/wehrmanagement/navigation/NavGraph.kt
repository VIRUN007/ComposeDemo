package com.kosign.webill.wehrmanagement.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kosign.webill.wehrmanagement.ui.screen.LocalizationExample
import com.kosign.webill.wehrmanagement.ui.screen.login.LoginScreen


@Composable
fun Navigation(
    navController: NavHostController, modifier: Modifier,
) {
    NavHost(navController, startDestination = "Localization", modifier) {
        composable("Login") {
            LoginScreen(
                modifier = modifier
            )
        }
        composable("Localization") {
            LocalizationExample(
                modifier = modifier,
                navController
            )
        }
    }
}
