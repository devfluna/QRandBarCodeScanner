package com.learning.qrbarcodescanner.ui.model

sealed class DeliveryScreenEvent{
    class DELETE(val delivery: PackageDelivery) : DeliveryScreenEvent()
    object SCAN: DeliveryScreenEvent()
    object ADD: DeliveryScreenEvent()
}
