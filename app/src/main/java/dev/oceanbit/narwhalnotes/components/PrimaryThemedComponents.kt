package dev.oceanbit.narwhalnotes.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PrimarySmallTopAppBar(
  modifier: Modifier = Modifier,
  title: @Composable () -> Unit,
  navigationIcon: @Composable () -> Unit = {},
  actions: @Composable RowScope.() -> Unit = {},
  scrollBehavior: TopAppBarScrollBehavior? = null
) {
  SmallTopAppBar(
    colors = TopAppBarDefaults.smallTopAppBarColors(
      containerColor = MaterialTheme.colorScheme.primary,
      titleContentColor = MaterialTheme.colorScheme.onPrimary,
      navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
      actionIconContentColor = MaterialTheme.colorScheme.onPrimary
    ),
    title = title,
    modifier = modifier,
    navigationIcon = navigationIcon,
    actions = actions,
    scrollBehavior = scrollBehavior
  )
}