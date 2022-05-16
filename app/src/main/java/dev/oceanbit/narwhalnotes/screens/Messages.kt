package dev.oceanbit.narwhalnotes.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.smallTopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import dev.oceanbit.narwhalnotes.compositions.MessagesList
import dev.oceanbit.narwhalnotes.types.MessageData
import dev.oceanbit.narwhalnotes.ui.theme.NarwhalNotesTheme
import kotlin.random.Random
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MessageScreen() {
    val messages = (0..16).map { i ->
        MessageData(
            LoremIpsum(Random.nextInt(3, 25)).values.joinToString(" "),
            Date()
        )
    }

    Scaffold(
        topBar = {
            SmallTopAppBar(
                colors = smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
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
            MessagesList(messages)
        }
    )
}

@Preview()
@Composable
fun DefaultPreview() {
    NarwhalNotesTheme {
        MessageScreen()
    }
}