package io.github.ryunen344.droidkaigies.shared.ui.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.ryunen344.droidkaigies.shared.theme.DroidkaigiesTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SplashRoute(onClick2022: () -> Unit, onClick2023: () -> Unit) {
    SplashScreen(
        onClick2022 = onClick2022,
        onClick2023 = onClick2023
    )
}

@Composable
private fun SplashScreen(onClick2022: () -> Unit, onClick2023: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Button(onClick2022) {
                Text("2022")
            }
            Button(onClick2023) {
                Text("2023")
            }
        }
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    DroidkaigiesTheme {
        SplashScreen(
            onClick2022 = {},
            onClick2023 = {}
        )
    }
}
