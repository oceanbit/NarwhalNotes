package dev.oceanbit.narwhalnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import dev.oceanbit.narwhalnotes.ui.theme.NarwhalNotesTheme
import java.text.SimpleDateFormat
import java.util.*

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

val TimeAMPMDisplay = SimpleDateFormat("HH:mm a");

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Message(message: String, sentTime: Date) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Card(modifier = Modifier.fillMaxWidth()) {
            Text(text = message, modifier = Modifier.padding(16.dp))
        }
        Text(text = TimeAMPMDisplay.format(sentTime), color=MaterialTheme.colorScheme.onBackground)
    }
}

data class MessageData(val message: String, val date: Date);

@Composable
fun MessageScreen() {
    val messages = (0..16).map { i ->
        MessageData("Test", Date())
    }


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(messages) { message ->
                Message("Testing", Date())
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