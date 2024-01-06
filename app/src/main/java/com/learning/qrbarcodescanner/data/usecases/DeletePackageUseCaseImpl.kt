package com.learning.qrbarcodescanner.data.usecases

import com.learning.qrbarcodescanner.data.database.PackageDeliveryEntity
import com.learning.qrbarcodescanner.data.mappers.UiStatusToDataStatusMapper
import com.learning.qrbarcodescanner.data.mappers.UiStatusToDataStatusMapperImpl
import com.learning.qrbarcodescanner.data.repository.DeliveryRepository
import com.learning.qrbarcodescanner.ui.model.PackageDelivery
import com.learning.qrbarcodescanner.ui.usecases.DeletePackageUseCase
import javax.inject.Inject

class DeletePackageUseCaseImpl @Inject constructor(
    private val repository: DeliveryRepository,
    private val mapper: UiStatusToDataStatusMapper = UiStatusToDataStatusMapperImpl()
) : DeletePackageUseCase {
    override suspend fun delete(delivery: PackageDelivery) {
        val packageDeliveryEntity = with(delivery) {
            PackageDeliveryEntity(id, itemName, trackingNumber, mapper.map(status))
        }

        repository.delete(packageDeliveryEntity)
    }
}