package dev.oceanbit.narwhalnotes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import dev.oceanbit.narwhalnotes.utils.NarwhalTimeUtils
import kotlin.random.Random
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Message(message: String, sentTime: Date) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Card(modifier = Modifier.fillMaxWidth()) {
            Text(text = message, modifier = Modifier.padding(16.dp))
        }
        Text(text = NarwhalTimeUtils.AMPMDisplay.format(sentTime), color= MaterialTheme.colorScheme.onBackground)
    }
}

data class MessageData(val message: String, val date: Date);

@Composable
fun MessagesList(messages: List<MessageData>) {
    LazyColumn(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        items(messages) { message ->
            Message(message.message, message.date)
        }
    }
}

@Composable
fun MessageScreen() {
    val messages = (0..16).map { i ->
        MessageData(
            LoremIpsum(Random.nextInt(3, 25)).values.joinToString(" "),
            Date()
        )
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        MessagesList(messages)
    }
}
