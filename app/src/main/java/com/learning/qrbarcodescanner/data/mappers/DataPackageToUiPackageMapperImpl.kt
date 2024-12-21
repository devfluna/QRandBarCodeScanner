package com.learning.qrbarcodescanner.data.mappers

import com.learning.qrbarcodescanner.data.database.DataDeliveryStatus
import com.learning.qrbarcodescanner.data.database.PackageDeliveryEntity
import com.learning.qrbarcodescanner.ui.model.DeliveryStatus
import com.learning.qrbarcodescanner.ui.model.PackageDelivery
import com.learning.qrbarcodescanner.utils.Mapper

/**
 * Interface defining the contract for a mapper responsible for transforming a data-layer
 * [PackageDeliveryEntity] into a UI-layer [PackageDelivery].
 */
interface DataPackageToUiPackageMapper : Mapper<PackageDeliveryEntity, PackageDelivery>

class DataPackageToUiPackageMapperImpl : DataPackageToUiPackageMapper {

    override fun map(input: PackageDeliveryEntity): PackageDelivery {
        return with(input) {
            PackageDelivery(
                id = id,
                itemName = itemName,
                trackingNumber = trackingNumber,
                status = when (status) {
                    DataDeliveryStatus.SHIPPED -> DeliveryStatus.Shipped()
                    DataDeliveryStatus.DELIVERED -> DeliveryStatus.Delivered()
                    DataDeliveryStatus.NOTIFIED -> DeliveryStatus.Notified()
                    DataDeliveryStatus.RECEIVED -> DeliveryStatus.Received()
                }
            )
        }
    }
}