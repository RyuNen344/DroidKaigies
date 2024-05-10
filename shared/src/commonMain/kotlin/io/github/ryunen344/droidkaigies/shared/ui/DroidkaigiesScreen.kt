package io.github.ryunen344.droidkaigies.shared.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.ryunen344.droidkaigies.shared.theme.DroidkaigiesTheme
import io.github.ryunen344.droidkaigies.shared.ui.splash.SplashRoute

@Composable
fun DroidkaigiesScreen() {
    DroidkaigiesTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Routes.Splash.route,
            modifier = Modifier.fillMaxSize()
        ) {
            composable(Routes.Splash.route) {
                SplashRoute(
                    onClick2022 = { navController.navigate(Routes.Top2022.route) },
                    onClick2023 = { navController.navigate(Routes.Top2023.route) }
                )
            }
            composable(Routes.Top2022.route) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text("2022")
                }
            }
            composable(Routes.Top2023.route) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text("2022")
                }
            }
        }
    }
}
