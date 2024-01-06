package com.learning.qrbarcodescanner.data.usecases

import com.learning.qrbarcodescanner.data.database.PackageDeliveryEntity
import com.learning.qrbarcodescanner.data.mappers.UiStatusToDataStatusMapper
import com.learning.qrbarcodescanner.data.mappers.UiStatusToDataStatusMapperImpl
import com.learning.qrbarcodescanner.data.repository.DeliveryRepository
import com.learning.qrbarcodescanner.ui.model.PackageDelivery
import com.learning.qrbarcodescanner.ui.usecases.InsertPackageUseCase
import javax.inject.Inject

class InsertPackageUseCaseImpl @Inject constructor(
    private val deliveryRepository: DeliveryRepository,
    private val mapper: UiStatusToDataStatusMapper = UiStatusToDataStatusMapperImpl()
) : InsertPackageUseCase {

    override suspend fun insert(delivery: PackageDelivery) {
        val packageDeliveryEntity = with(delivery) {
            PackageDeliveryEntity(
                itemName = itemName,
                trackingNumber = trackingNumber,
                status = mapper.map(status)
            )
        }

        deliveryRepository.insert(packageDeliveryEntity)
    }
}