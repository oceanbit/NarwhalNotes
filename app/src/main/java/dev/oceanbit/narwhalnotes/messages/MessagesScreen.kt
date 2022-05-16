package dev.oceanbit.narwhalnotes.messages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults.textButtonColors
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
import dev.oceanbit.narwhalnotes.R
import dev.oceanbit.narwhalnotes.components.SelectableCard
import kotlinx.coroutines.launch
import kotlin.random.Random
import java.util.*


@Composable
private fun ChatMessage(
  modifier: Modifier = Modifier,
  message: String,
  sentTime: Date,
  selected: Boolean,
  onLongPress: () -> Unit,
  onPress: () -> Unit,
) {
  Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
    SelectableCard(modifier = Modifier.fillMaxWidth(), selected = selected, onLongPress = onLongPress, onPress = onPress) {
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
  messages: List<Message>,
  selectedMessages: MutableList<Long>,
  state: LazyListState?
) {
  LazyColumn(
    state = state ?: LazyListState(),
    modifier = modifier.padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp),
  ) {
    items(messages, { item -> item.uid }) { item ->
      val selected = selectedMessages.contains(item.uid)
      ChatMessage(
        message = item.message,
        sentTime = item.sent,
        selected = selected,
        onPress = {
          // Do not move this out of this block, otherwise they're inside of a closure and never update
          val selectModeEnabled = selectedMessages.size != 0
          val selectedLocal = selectedMessages.contains(item.uid)
          if (selectModeEnabled) {
            if (selectedLocal) selectedMessages.remove(item.uid) else selectedMessages.add(item.uid)
          }
        },
        onLongPress = {
          val selectModeEnabled = selectedMessages.size != 0
          if (!selectModeEnabled) selectedMessages.add(item.uid)
        }
      )
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageScreenUI(
  messages: List<Message>,
  selectedMessages: MutableList<Long>,
  sendMessage: () -> Unit,
  currentText: MutableState<String>,
  listState: LazyListState? = null
) {
  Scaffold(
    topBar = {
      PrimarySmallTopAppBar(
        title = { Text("NarwhalNotes") },
        navigationIcon = {
          IconButton(onClick = { /* doSomething() */ }) {
            Icon(
              imageVector = Icons.Filled.Settings,
              contentDescription = stringResource(R.string.messages_settings_icon_label)
            )
          }
        },
        actions = {
          IconButton(onClick = { /* doSomething() */ }) {
            Icon(
              imageVector = Icons.Filled.Search,
              contentDescription = stringResource(R.string.messages_search_icon_label)
            )
          }
        }
      )
    },
    content = { innerPadding ->
      MessagesList(
        messages = messages,
        modifier = Modifier.padding(innerPadding),
        state = listState,
        selectedMessages = selectedMessages
      )
    },
    bottomBar = {
      BottomAppBar(
        containerColor = MaterialTheme.colorScheme.tertiary,
        contentColor = MaterialTheme.colorScheme.onTertiary
      ) {
        TertiaryTextField(
          value = currentText.value,
          placeholder = { Text(stringResource(R.string.send_message_placeholder)) },
          onValueChange = { value -> currentText.value = value },
          modifier = Modifier.weight(1f)
        )
        TextButton(
          colors = textButtonColors(
            contentColor = MaterialTheme.colorScheme.onTertiary
          ), onClick = sendMessage
        ) {
          Icon(Icons.Filled.Send, stringResource(R.string.send_message_icon_label))
        }
      }
    }
  )
}

@Composable
fun MessageScreen(
  viewModel: MessageListViewModel = viewModel()
) {
  val currentText = rememberSaveable { mutableStateOf("") }
  val selectedMessages = remember { mutableStateListOf<Long>() }
  val listState = rememberLazyListState(Int.MAX_VALUE)
  val coroutineScope = rememberCoroutineScope()

  fun sendMessage() {
    val newMsg = Message(message = currentText.value, sent = Date())
    viewModel.sendMessage(newMsg)
    currentText.value = ""
    coroutineScope.launch {
      listState.animateScrollToItem(index = viewModel.messages.value!!.size + 1)
    }
  }

  val messages by viewModel.messages.observeAsState()

  MessageScreenUI(
    sendMessage = ::sendMessage,
    currentText = currentText,
    messages = messages ?: emptyList(),
    listState = listState,
    selectedMessages = selectedMessages
  )
}

@Preview
@Composable
fun MessagesPreview() {
  NarwhalNotesTheme {
    val currentText = rememberSaveable { mutableStateOf("") }
    val selectedMessages = remember { mutableStateListOf<Long>() }

    val messages = (0..2).map { i ->
      val msg = LoremIpsum(Random.nextInt(3, 25)).values.joinToString(" ")
      Message(
        msg,
        Date(),
        i.toLong()
      )
    }

    MessageScreenUI(
      messages = messages,
      currentText = currentText,
      sendMessage = {},
      selectedMessages = selectedMessages
    )
  }
}