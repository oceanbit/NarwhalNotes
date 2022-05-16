package dev.oceanbit.narwhalnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import dev.oceanbit.narwhalnotes.data.AppDatabase
import dev.oceanbit.narwhalnotes.messages.MessageScreen
import dev.oceanbit.narwhalnotes.ui.theme.NarwhalNotesTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val db = Room.databaseBuilder(
      applicationContext,
      AppDatabase::class.java, "database-name"
    ).build()

    setContent {
      NarwhalNotesTheme {
        // A surface container using the 'background' color from the theme
        MessageScreen(db)
      }
    }
  }
}

@Preview()
@Composable
fun DefaultPreview() {
  NarwhalNotesTheme {
    MessageScreen()
  }
}