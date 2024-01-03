package com.learning.qrbarcodescanner.ui.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.learning.qrbarcodescanner.ui.model.DeliveryStatus
import com.learning.qrbarcodescanner.ui.theme.DeliveredSelected
import com.learning.qrbarcodescanner.ui.theme.NotifiedSelected
import com.learning.qrbarcodescanner.ui.theme.ReceivedSelected
import com.learning.qrbarcodescanner.ui.theme.ShippedSelected

@Composable
fun StatusSelection(
    status: DeliveryStatus,
    modifier: Modifier = Modifier,
    onStatusSelection: (DeliveryStatus) -> Unit
) {
    Row(modifier = modifier.padding(8.dp), horizontalArrangement = Arrangement.SpaceAround) {
        StatusChip(
            status = DeliveryStatus.Shipped(),
            color = if (status is DeliveryStatus.Shipped) ShippedSelected else Color.Transparent,
            onClick = onStatusSelection
        )
        StatusChip(
            status = DeliveryStatus.Delivered(),
            color = if (status is DeliveryStatus.Delivered) DeliveredSelected else Color.Transparent,
            onClick = onStatusSelection
        )
        StatusChip(
            status = DeliveryStatus.Notified(),
            color = if (status is DeliveryStatus.Notified) NotifiedSelected else Color.Transparent,
            onClick = onStatusSelection
        )

        StatusChip(
            status = DeliveryStatus.Received(),
            color = if (status is DeliveryStatus.Received) ReceivedSelected else Color.Transparent,
            onClick = onStatusSelection
        )
    }
}