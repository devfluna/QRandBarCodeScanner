package com.learning.qrbarcodescanner.data.usecases

import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import com.learning.qrbarcodescanner.data.database.DataDeliveryStatus
import com.learning.qrbarcodescanner.data.database.PackageDeliveryEntity
import com.learning.qrbarcodescanner.data.repository.DeliveryRepository
import com.learning.qrbarcodescanner.ui.usecases.HandleTrackingNumberUseCase
import javax.inject.Inject

class HandleTrackingNumberUseCaseImpl @Inject constructor(
    val deliveryRepository: DeliveryRepository
) : HandleTrackingNumberUseCase {
    override suspend fun invoke(trackingNumber: String) {
        try {
            val delivery = deliveryRepository.locate(trackingNumber.toUpperCase(Locale.current))
            val updatedDelivery = delivery.copy(status = DataDeliveryStatus.RECEIVED)

            deliveryRepository.update(updatedDelivery)
        } catch (e: java.lang.Exception) {
            val unknownDelivery = PackageDeliveryEntity(
                itemName = "Unknown Delivery",
                trackingNumber = trackingNumber,
                status = DataDeliveryStatus.RECEIVED
            )

            deliveryRepository.insert(unknownDelivery)
        }
    }
}