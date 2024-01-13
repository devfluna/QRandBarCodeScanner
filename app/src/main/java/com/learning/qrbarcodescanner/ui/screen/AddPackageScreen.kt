package com.learning.qrbarcodescanner.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.learning.qrbarcodescanner.ui.model.DeliveryStatus
import com.learning.qrbarcodescanner.ui.model.PackageDelivery
import com.learning.qrbarcodescanner.ui.screen.components.StatusSelection
import com.learning.qrbarcodescanner.ui.theme.QRBarCodeScannerTheme

@Composable
fun AddPackageScreen(
    modifier: Modifier = Modifier,
    onSaveClick: (PackageDelivery) -> Unit
) {
    Surface(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
        ) {

            Text(text = "Enter an item description")
            Spacer(modifier = Modifier.height(8.dp))

            var itemName by remember { mutableStateOf("") }
            TextField(
                value = itemName,
                onValueChange = { value: String -> itemName = value }
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Enter item's Tracking Number")
            Spacer(modifier = Modifier.height(8.dp))

            var trackingNumber by remember { mutableStateOf("") }
            TextField(
                value = trackingNumber,
                onValueChange = { trackingNumber = it.toUpperCase(Locale.current) }
            )

            Spacer(modifier = Modifier.height(16.dp))
            var status by remember { mutableStateOf<DeliveryStatus>(DeliveryStatus.Shipped()) }

            StatusSelection(
                status = status,
                onStatusSelection = { deliveryStatus -> status = deliveryStatus }
            )

            Spacer(modifier = Modifier.height(16.dp))
            val isVisible = itemName.isNotEmpty() && trackingNumber.isNotEmpty()
            AnimatedVisibility(visible = isVisible) {
                Button(onClick = {
                    val delivery = PackageDelivery(1, itemName, trackingNumber, status)
                    onSaveClick(delivery)
                }) {
                    Text(text = "SAVE")
                }
            }
        }
    }
}

@Preview
@Composable
fun AddPackageScreenPreview() {
    QRBarCodeScannerTheme {
        AddPackageScreen(modifier = Modifier.fillMaxSize()) {}
    }
}