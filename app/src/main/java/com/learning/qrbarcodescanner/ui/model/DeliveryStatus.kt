package com.learning.qrbarcodescanner.ui.model

import androidx.compose.ui.graphics.Color

sealed class DeliveryStatus(val color: Color = Color.Red) {
    class Shipped : DeliveryStatus(color = Color(0xFF9fc5e8))
    class Delivered : DeliveryStatus(color = Color(0xFFb6d7a8))
    class Notified : DeliveryStatus(color = Color(0xFFffe599))
    class Received : DeliveryStatus(color = Color(0xFFbcbcbc))
}
