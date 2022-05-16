package dev.oceanbit.narwhalnotes.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectableCard(
  modifier: Modifier = Modifier,
  selected: MutableState<Boolean>,
  content: @Composable ColumnScope.() -> Unit
) {
  val containerColor = if (selected.value) MaterialTheme.colorScheme.inversePrimary else MaterialTheme.colorScheme.secondary;
  val baseModifiers = if (selected.value) Modifier.border(width = 4.dp, color = MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(16.dp)) else Modifier;
  Card(
    modifier = Modifier.then(modifier).then(baseModifiers),
    content = content,
    colors = cardColors(
      containerColor = containerColor
    ),
    onClick = {
      selected.value = !selected.value
    }
  )
}

@Preview
@Composable
fun LightSelectableCardPreview() {
  var selected = remember { mutableStateOf(false) }
  Column(verticalArrangement = Arrangement.spacedBy(32.dp)) {
    SelectableCard(selected = selected) {
      Text(text = "Testing 123")
    }
    SelectableCard(selected = selected) {
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
    SelectableCard(selected = selected) {
      Text(text = "Testing 123")
    }
    SelectableCard(selected = selected) {
      Text(text = "Testing 123")
    }
  }
}
