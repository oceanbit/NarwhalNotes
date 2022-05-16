package dev.oceanbit.narwhalnotes.compositions

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
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        modifier = modifier,
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