package com.learning.qrbarcodescanner.utils

import com.learning.qrbarcodescanner.ui.model.DeliveryStatus
import com.learning.qrbarcodescanner.data.database.DataDeliveryStatus
import com.learning.qrbarcodescanner.ui.model.PackageDelivery

fun DeliveryStatus.getStatusName(): String = when (this) {
    is DeliveryStatus.Delivered -> "Delivered"
    is DeliveryStatus.Notified -> "Notified"
    is DeliveryStatus.Received -> "Received"
    is DeliveryStatus.Shipped -> "Shipped"
}

fun DeliveryStatus.toEnum(): DataDeliveryStatus = when (this) {
    is DeliveryStatus.Shipped -> DataDeliveryStatus.SHIPPED
    is DeliveryStatus.Delivered -> DataDeliveryStatus.DELIVERED
    is DeliveryStatus.Notified -> DataDeliveryStatus.NOTIFIED
    is DeliveryStatus.Received -> DataDeliveryStatus.RECEIVED
}

val FakePackageList = listOf(
    PackageDelivery(
        1,
        "Amazon",
        "1Z123456",
        DeliveryStatus.Shipped()
    ),
    PackageDelivery(
        2,
        "BestBuy",
        "1ZQWERTY",
        DeliveryStatus.Delivered()
    ),
    PackageDelivery(
        3,
        "Tactical 5.11",
        "1ZASDFGH",
        DeliveryStatus.Notified()
    ),
    PackageDelivery(
        4,
        "MONAS CHINAS INC.",
        "1-69-4-ME",
        DeliveryStatus.Received()
    )
)