package com.learning.qrbarcodescanner.ui.model


data class PackageDelivery(
    val id: Int = 0,
    val itemName: String,
    val trackingNumber: String,
    val status: DeliveryStatus,
)
