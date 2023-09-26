package ru.mishenko.maksim.common.ui.selectMode

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.mishenko.maksim.common.ui.custom.CustomSwitch

@Composable
fun SelectModeScreen(
    switchValue: Boolean,
    onChangeValue: (Boolean) -> Unit,
    onDone: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomSwitch(
            modifier = Modifier.fillMaxWidth().height(150.dp),
            value = switchValue,
            onChangeValue = onChangeValue
        )
        Text(if (switchValue) "Server" else "Client")
        Button(onClick = onDone) { Text("Next") }
    }
}