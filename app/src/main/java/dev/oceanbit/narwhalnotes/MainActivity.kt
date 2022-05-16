package dev.oceanbit.narwhalnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.oceanbit.narwhalnotes.screens.MessageScreen
import dev.oceanbit.narwhalnotes.ui.theme.NarwhalNotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NarwhalNotesTheme {
                // A surface container using the 'background' color from the theme
                MessageScreen()
            }
        }
    }
}
