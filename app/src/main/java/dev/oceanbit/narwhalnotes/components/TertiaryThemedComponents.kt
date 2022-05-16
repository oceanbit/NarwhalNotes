package dev.oceanbit.narwhalnotes.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.outlinedTextFieldColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TertiaryTextField(
  modifier: Modifier = Modifier,
  value: String,
  onValueChange: (String) -> Unit,
  placeholder: @Composable (() -> Unit)? = null,
) {
  OutlinedTextField(
    value = value,
    modifier = modifier,
    placeholder = placeholder,
    onValueChange = onValueChange,
    colors = outlinedTextFieldColors(
      unfocusedBorderColor = Color.Transparent,
      disabledBorderColor = Color.Transparent,
      placeholderColor = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.6f),
      disabledPlaceholderColor = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.2f),
      textColor = MaterialTheme.colorScheme.onTertiary
    )
  )
}