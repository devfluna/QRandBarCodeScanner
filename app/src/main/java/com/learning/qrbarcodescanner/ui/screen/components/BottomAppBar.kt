package com.learning.qrbarcodescanner.ui.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.learning.qrbarcodescanner.ui.model.DeliveryScreenEvent
import com.learning.qrbarcodescanner.ui.theme.QRBarCodeScannerTheme

@Composable
fun BottomDeliveryActionBar(
    onEvent: (DeliveryScreenEvent) -> Unit
) {
    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { onEvent(DeliveryScreenEvent.SCAN) }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "")
            }

            IconButton(onClick = { onEvent(DeliveryScreenEvent.ADD) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        }
    }
}

@Composable
@Preview
fun BottomDeliveryActionBarPreview() {
    QRBarCodeScannerTheme {
        BottomDeliveryActionBar(onEvent = {})
    }

}