package com.learning.qrbarcodescanner.ui.model


data class PackageDelivery(
    val id: Int = 1,
    val itemName: String,
    val trackingNumber: String,
    val status: DeliveryStatus,
)
