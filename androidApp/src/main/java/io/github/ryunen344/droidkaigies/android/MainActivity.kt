package io.github.ryunen344.droidkaigies.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import io.github.ryunen344.droidkaigies.shared.ui.DroidkaigiesScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DroidkaigiesScreen()
        }
    }
}
