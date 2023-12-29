package com.learning.qrbarcodescanner.ui.model

import androidx.compose.ui.graphics.Color

sealed class DeliveryStatus(val color: Color = Color.Red) {
    class Shipped : DeliveryStatus(color = Color(0xFF7BD3EA))
    class Delivered : DeliveryStatus(color = Color(0xFFF6F7C4))
    class Notified : DeliveryStatus(color = Color(0xFFA1EEBD))
    class Received : DeliveryStatus(color = Color(0xFF9BB8CD))
}
