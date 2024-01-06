package com.learning.qrbarcodescanner.ui.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DismissBackgroundIcons(dismissState: DismissState) {
    val color = getDismissDirectionColor(dismissState.dismissDirection)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "",
            modifier = Modifier.align(Alignment.CenterStart)
        )
    }
}

private fun getDismissDirectionColor(direction: DismissDirection?): Color {
    return when (direction) {
        DismissDirection.StartToEnd -> Color.Red
        else -> Color.Transparent
    }
}