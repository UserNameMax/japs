package ru.mishenko.maksim.common.ui.custom

import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TopBar(
    name: String,
    onBack: () -> Unit,
    onInputName: (String) -> Unit
) {
    var isEdit by remember { mutableStateOf(false) }
    TopAppBar(
        title = {
            if (isEdit) {
                TextField(
                    modifier = Modifier.onPreviewKeyEvent {
                        isEdit = it.key != Key.Enter
                        false
                    },
                    value = name,
                    onValueChange = onInputName,
                    singleLine = true,
                    keyboardActions = KeyboardActions(onAny = { isEdit = false })
                )
            } else {
                Text(modifier = Modifier.clickable { isEdit = true }, text = name)
            }
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null)
            }
        },
    )
}