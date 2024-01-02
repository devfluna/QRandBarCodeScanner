package com.learning.qrbarcodescanner.data.mappers

import com.learning.qrbarcodescanner.data.database.DataDeliveryStatus
import com.learning.qrbarcodescanner.data.database.PackageDeliveryEntity
import com.learning.qrbarcodescanner.ui.model.DeliveryStatus
import com.learning.qrbarcodescanner.ui.model.PackageDelivery
import com.learning.qrbarcodescanner.utils.Mapper


interface UiPackageToDataPackageMapper :
    Mapper<PackageDelivery, PackageDeliveryEntity>

class UiPackageToDataPackageMapperImpl :
    UiPackageToDataPackageMapper {
    override fun map(input: PackageDelivery): PackageDeliveryEntity {
        return with(input) {
            PackageDeliveryEntity(
                id = id,
                itemName = itemName,
                trackingNumber = trackingNumber,
                status = when (status) {
                    is DeliveryStatus.Delivered -> DataDeliveryStatus.DELIVERED
                    is DeliveryStatus.Notified -> DataDeliveryStatus.NOTIFIED
                    is DeliveryStatus.Received -> DataDeliveryStatus.RECEIVED
                    is DeliveryStatus.Shipped -> DataDeliveryStatus.SHIPPED
                }
            )
        }
    }
}