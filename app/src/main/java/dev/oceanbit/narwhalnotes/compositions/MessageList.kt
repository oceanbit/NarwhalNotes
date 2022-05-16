package dev.oceanbit.narwhalnotes.compositions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.oceanbit.narwhalnotes.types.MessageData
import dev.oceanbit.narwhalnotes.utils.NarwhalTimeUtils
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

@Composable
fun MessagesList(messages: List<MessageData>) {
    LazyColumn(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        items(messages) { message ->
            Message(message.message, message.date)
        }
    }
}
