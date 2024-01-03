package com.learning.qrbarcodescanner.ui.screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.learning.qrbarcodescanner.ui.model.DeliveryStatus
import com.learning.qrbarcodescanner.ui.model.PackageDelivery
import com.learning.qrbarcodescanner.ui.theme.QRBarCodeScannerTheme
import com.learning.qrbarcodescanner.utils.FakePackageList
import com.learning.qrbarcodescanner.utils.getStatusName

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

@Preview
@Composable
fun PackageCardPreview() {
    QRBarCodeScannerTheme {
        Column {
            PackageCard(
                modifier = Modifier.fillMaxWidth(),
                packageDelivery = FakePackageList[0],
                onStatusSelected = {}
            )

            PackageCard(
                modifier = Modifier.fillMaxWidth(),
                packageDelivery = FakePackageList[1],
                onStatusSelected = {}
            )

            PackageCard(
                modifier = Modifier.fillMaxWidth(),
                packageDelivery = FakePackageList[2],
                onStatusSelected = {}
            )

            PackageCard(
                modifier = Modifier.fillMaxWidth(),
                packageDelivery = FakePackageList[3],
                onStatusSelected = {}
            )
        }
    }
}