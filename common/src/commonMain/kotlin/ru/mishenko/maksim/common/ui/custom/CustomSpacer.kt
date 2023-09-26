package ru.mishenko.maksim.common.ui.custom

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun ColumnScope.Spacer(height: Dp) {
    Spacer(modifier = Modifier.height(height))
}

@Composable
fun RowScope.Spacer(width: Dp) {
    Spacer(modifier = Modifier.width(width))
}