package com.learning.qrbarcodescanner.ui.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.learning.qrbarcodescanner.ui.model.DeliveryStatus
import com.learning.qrbarcodescanner.utils.getStatusName

@Composable
fun StatusChip(status: DeliveryStatus, color: Color, onClick: (DeliveryStatus) -> Unit) {
    val chipName = status.getStatusName()

    Text(text = chipName, modifier = Modifier
        .clip(CircleShape)
        .clickable { onClick(status) }
        .background(color)
        .padding(4.dp))
}