package ru.mishenko.maksim.common.ui.custom

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

//https://stackoverflow.com/questions/67640056/jetpack-compose-switch-trackwidth-trackstrokewidth-thumbdiameter
@Composable
fun CustomSwitch(
    modifier: Modifier,
    value: Boolean,
    onChangeValue: (Boolean) -> Unit,
    strokeWidth: Dp = 2.dp,
    checkedTrackColor: Color = MaterialTheme.colors.secondaryVariant,
    uncheckedTrackColor: Color = MaterialTheme.colors.onSurface,
    gapBetweenThumbAndTrackEdge: Dp = 4.dp
) {

    var componentHeight by remember { mutableStateOf(0.dp) }
    var componentWidth by remember { mutableStateOf(0.dp) }

    val thumbRadius = (componentHeight / 2) - gapBetweenThumbAndTrackEdge

    val localDensity = LocalDensity.current

    // To move thumb, we need to calculate the position (along x axis)
    val animatePosition = animateFloatAsState(
        targetValue = if (value)
            with(localDensity) { (componentWidth - thumbRadius - gapBetweenThumbAndTrackEdge).toPx() }
        else
            with(localDensity) { (thumbRadius + gapBetweenThumbAndTrackEdge).toPx() }
    )

    Canvas(
        modifier = modifier
            .onGloballyPositioned {
                componentHeight = with(localDensity) {
                    it.size.height.toDp()
                }
                componentWidth = with(localDensity) {
                    it.size.width.toDp()
                }
            }
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        // This is called when the user taps on the canvas
                        onChangeValue(!value)
                    }
                )
            }
    ) {
        // Track
        drawRoundRect(
            color = if (value) checkedTrackColor else uncheckedTrackColor,
            cornerRadius = CornerRadius(x = 10.dp.toPx(), y = 10.dp.toPx()),
            style = Stroke(width = strokeWidth.toPx())
        )

        // Thumb
        drawCircle(
            color = if (value) checkedTrackColor else uncheckedTrackColor,
            radius = thumbRadius.toPx(),
            center = Offset(
                x = animatePosition.value,
                y = size.height / 2
            )
        )
    }
}