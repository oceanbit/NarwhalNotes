package dev.oceanbit.narwhalnotes.messages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults.textButtonColors
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import dev.oceanbit.narwhalnotes.components.PrimarySmallTopAppBar
import dev.oceanbit.narwhalnotes.components.TertiaryTextField
import dev.oceanbit.narwhalnotes.types.MessageData
import dev.oceanbit.narwhalnotes.ui.theme.NarwhalNotesTheme
import dev.oceanbit.narwhalnotes.utils.NarwhalTimeUtils
import kotlin.random.Random
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Message(
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
  messages: List<MessageData>
) {
  LazyColumn(
    modifier = modifier.padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp),
  ) {
    items(messages) { message ->
      Message(
        message = message.message,
        sentTime = message.date
      )
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MessageScreen() {
  val messages = (0..16).map { i ->
    val msg = LoremIpsum(Random.nextInt(3, 25)).values.joinToString(" ");
    MessageData(
      msg,
      Date()
    )
  }

  var currentText by rememberSaveable { mutableStateOf("") };

  Scaffold(
    topBar = {
      PrimarySmallTopAppBar(
        title = { Text("NarwhalNotes") },
        navigationIcon = {
          IconButton(onClick = { /* doSomething() */ }) {
            Icon(
              imageVector = Icons.Filled.Menu,
              contentDescription = "Localized description"
            )
          }
        },
        actions = {
          IconButton(onClick = { /* doSomething() */ }) {
            Icon(
              imageVector = Icons.Filled.Search,
              contentDescription = "Localized description"
            )
          }
        }
      )
    },
    content = { _ ->
      MessagesList(messages = messages)
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
        TextButton(colors = textButtonColors(
          contentColor = MaterialTheme.colorScheme.onTertiary
        ), onClick = { /*TODO*/ }) {
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