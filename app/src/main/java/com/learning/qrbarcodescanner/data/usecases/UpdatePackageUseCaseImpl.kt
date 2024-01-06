package com.learning.qrbarcodescanner.data.usecases

import com.learning.qrbarcodescanner.data.database.PackageDeliveryEntity
import com.learning.qrbarcodescanner.data.mappers.UiStatusToDataStatusMapper
import com.learning.qrbarcodescanner.data.mappers.UiStatusToDataStatusMapperImpl
import com.learning.qrbarcodescanner.data.repository.DeliveryRepository
import com.learning.qrbarcodescanner.ui.model.PackageDelivery
import com.learning.qrbarcodescanner.ui.usecases.UpdatePackageUseCase
import javax.inject.Inject

class UpdatePackageUseCaseImpl @Inject constructor(
    private val repository: DeliveryRepository,
    private val mapper: UiStatusToDataStatusMapper = UiStatusToDataStatusMapperImpl()
) : UpdatePackageUseCase {

    override suspend fun update(delivery: PackageDelivery) {
        val packageDeliveryEntity = with(delivery) {
            PackageDeliveryEntity(id, itemName, trackingNumber, mapper.map(status))
        }

        repository.update(packageDeliveryEntity)
    }
}