package com.learning.qrbarcodescanner.data.mappers

import com.learning.qrbarcodescanner.data.database.DataDeliveryStatus
import com.learning.qrbarcodescanner.ui.model.DeliveryStatus
import com.learning.qrbarcodescanner.utils.Mapper

/**
 * Interface defining the contract for a mapper responsible for transforming a UI-layer
 * [DeliveryStatus] into a data-layer [DataDeliveryStatus].
 */
interface UiStatusToDataStatusMapper :
    Mapper<DeliveryStatus, DataDeliveryStatus>

class UiStatusToDataStatusMapperImpl :
    UiStatusToDataStatusMapper {
    override fun map(input: DeliveryStatus): DataDeliveryStatus {
        return when (input) {
            is DeliveryStatus.Delivered -> DataDeliveryStatus.DELIVERED
            is DeliveryStatus.Notified -> DataDeliveryStatus.NOTIFIED
            is DeliveryStatus.Received -> DataDeliveryStatus.RECEIVED
            is DeliveryStatus.Shipped -> DataDeliveryStatus.SHIPPED
        }
    }
}