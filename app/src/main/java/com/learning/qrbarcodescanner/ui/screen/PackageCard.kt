package com.learning.qrbarcodescanner.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.learning.qrbarcodescanner.utils.FakePackageList
import com.learning.qrbarcodescanner.utils.getStatusName
import com.learning.qrbarcodescanner.ui.model.DeliveryStatus
import com.learning.qrbarcodescanner.ui.model.PackageDelivery
import com.learning.qrbarcodescanner.ui.theme.SelectedStatusColor

@Composable
fun PackageCard(
    modifier: Modifier = Modifier,
    packageDelivery: PackageDelivery,
    onStatusSelected: (DeliveryStatus) -> Unit
) {
    var areOptionsVisible by remember { mutableStateOf(false) }

    Card(
        elevation = 8.dp,
        backgroundColor = packageDelivery.status.color,
        modifier = Modifier.clickable { areOptionsVisible = !areOptionsVisible }
    ) {
        Column(modifier = modifier.padding(8.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = modifier) {
                Text(
                    text = packageDelivery.itemName,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = packageDelivery.status.getStatusName(),
                    fontWeight = FontWeight.Bold
                )
            }

            Text(text = packageDelivery.trackingNumber, style = MaterialTheme.typography.subtitle1)

            Divider()

            AnimatedVisibility(visible = areOptionsVisible) {
                StatusSelection(
                    status = packageDelivery.status,
                    modifier = modifier,
                    onStatusSelection = { status -> onStatusSelected(status) }
                )
            }
        }
    }
}

@Composable
fun StatusSelection(
    status: DeliveryStatus,
    modifier: Modifier = Modifier,
    onStatusSelection: (DeliveryStatus) -> Unit
) {
    Row(modifier = modifier.padding(8.dp), horizontalArrangement = Arrangement.SpaceAround) {
        StatusChip(
            status = DeliveryStatus.Shipped(),
            color = if (status is DeliveryStatus.Shipped) SelectedStatusColor else Color.Transparent,
            onClick = onStatusSelection
        )
        StatusChip(
            status = DeliveryStatus.Delivered(),
            color = if (status is DeliveryStatus.Delivered) SelectedStatusColor else Color.Transparent,
            onClick = onStatusSelection
        )
        StatusChip(
            status = DeliveryStatus.Notified(),
            color = if (status is DeliveryStatus.Notified) SelectedStatusColor else Color.Transparent,
            onClick = onStatusSelection
        )

        StatusChip(
            status = DeliveryStatus.Received(),
            color = if (status is DeliveryStatus.Received) SelectedStatusColor else Color.Transparent,
            onClick = onStatusSelection
        )
    }
}

@Composable
fun StatusChip(status: DeliveryStatus, color: Color, onClick: (DeliveryStatus) -> Unit) {
    val chipName = status.getStatusName()

    Text(text = chipName, modifier = Modifier
        .clip(CircleShape)
        .clickable { onClick(status) }
        .background(color)
        .padding(4.dp))
}

@Preview
@Composable
fun PackageCardPreview() {
    PackageCard(
        modifier = Modifier.fillMaxWidth(),
        packageDelivery = FakePackageList.first(),
        onStatusSelected = {}
    )
}