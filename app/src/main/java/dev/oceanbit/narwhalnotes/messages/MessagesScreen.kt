package dev.oceanbit.narwhalnotes.messages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults.textButtonColors
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import dev.oceanbit.narwhalnotes.components.PrimarySmallTopAppBar
import dev.oceanbit.narwhalnotes.components.TertiaryTextField
import dev.oceanbit.narwhalnotes.data.Message
import dev.oceanbit.narwhalnotes.ui.theme.NarwhalNotesTheme
import dev.oceanbit.narwhalnotes.utils.NarwhalTimeUtils
import dev.oceanbit.narwhalnotes.viewmodels.MessageListViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.random.Random
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChatMessage(
  modifier: Modifier = Modifier,
  message: String,
  sentTime: Date
) {
  Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
    Card(modifier = Modifier.fillMaxWidth()) {
      Text(text = message, modifier = Modifier.padding(16.dp))
    }
    Text(
      text = NarwhalTimeUtils.AMPMDisplay.format(sentTime),
      color = MaterialTheme.colorScheme.onBackground
    )
  }
}

@Composable
private fun MessagesList(
  modifier: Modifier = Modifier,
  messages: List<Message>
) {
  LazyColumn(
    modifier = modifier.padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp),
  ) {
    items(messages) { (message, date) ->
      ChatMessage(
        message = message,
        sentTime = date
      )
    }
  }
}

val messages = (0..2).map { i ->
  val msg = LoremIpsum(Random.nextInt(3, 25)).values.joinToString(" ");
  Message(
    msg,
    Date()
  )
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MessageScreen(
  viewModel: MessageListViewModel = viewModel()
) {

  var currentText by remember { mutableStateOf("") }

  val currentList by lazy {
    viewModel.messages
  }

  val messages by viewModel.messages.observeAsState();

  fun sendMessage() {
    val newMsg = Message(message = currentText, sent = Date());
    viewModel.sendMessage(newMsg);
    currentText = "";
  }

  Scaffold(
    topBar = {
      PrimarySmallTopAppBar(
        title = { Text("NarwhalNotes") },
        navigationIcon = {
          IconButton(onClick = { /* doSomething() */ }) {
            Icon(
              imageVector = Icons.Filled.Settings,
              contentDescription = "Settings"
            )
          }
        },
        actions = {
          IconButton(onClick = { /* doSomething() */ }) {
            Icon(
              imageVector = Icons.Filled.Search,
              contentDescription = "Search through your notes"
            )
          }
        }
      )
    },
    content = { innerPadding ->
      messages?.let { MessagesList(messages = it, modifier = Modifier.padding(innerPadding)) }
    },
    bottomBar = {
      BottomAppBar(
        containerColor = MaterialTheme.colorScheme.tertiary,
        contentColor = MaterialTheme.colorScheme.onTertiary
      ) {
        TertiaryTextField(
          value = currentText,
          placeholder = { Text("Message") },
          onValueChange = { value -> currentText = value },
          modifier = Modifier.weight(1f)
        )
        TextButton(
          colors = textButtonColors(
            contentColor = MaterialTheme.colorScheme.onTertiary
          ), onClick = ::sendMessage
        ) {
          Icon(Icons.Filled.Send, "Send the message")
        }
      }
    }
  )
}

@Preview()
@Composable
fun MessagesPreview() {
  NarwhalNotesTheme {
    MessageScreen()
  }
}