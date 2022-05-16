package dev.oceanbit.narwhalnotes.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectableCard(
  modifier: Modifier = Modifier,
  selected: Boolean,
  onSelected: () -> Unit,
  content: @Composable ColumnScope.() -> Unit
) {
  val containerColor = if (selected) MaterialTheme.colorScheme.inversePrimary else MaterialTheme.colorScheme.secondary;
  val selectedModifiers = if (selected) Modifier.border(width = 4.dp, color = MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(16.dp)) else Modifier;
  Box(modifier = Modifier.pointerInput(Unit) {
    detectTapGestures(
      onPress = { /* Called when the gesture starts */ },
      onDoubleTap = { /* Called on Double Tap */ },
      onLongPress = {onSelected()},
      onTap = { /* Called on Tap */ }
    )
  }) {
    Card(
      modifier = Modifier.then(modifier).then(selectedModifiers),
      content = content,
      colors = cardColors(
        containerColor = containerColor
      ),
    )
  }
}

@Preview
@Composable
fun LightSelectableCardPreview() {
  var selected = remember { mutableStateOf(false) }
  Column(verticalArrangement = Arrangement.spacedBy(32.dp)) {
    SelectableCard(selected = selected.value, onSelected = {
      selected.value = !selected.value
    }) {
      Text(text = "Testing 123")
    }
    SelectableCard(selected = selected.value, onSelected = {
      selected.value = !selected.value
    }) {
      Text(text = "Testing 123")
    }
  }
}

@Preview(
  uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun DarkSelectableCardPreview() {
  var selected = remember { mutableStateOf(false) }
  Column(verticalArrangement = Arrangement.spacedBy(32.dp)) {
    SelectableCard(selected = selected.value, onSelected = {
      selected.value = !selected.value
    }) {
      Text(text = "Testing 123")
    }
    SelectableCard(selected = selected.value, onSelected = {
      selected.value = !selected.value
    }) {
      Text(text = "Testing 123")
    }
  }
}
