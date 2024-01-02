package com.learning.qrbarcodescanner.data.usecases

import com.learning.qrbarcodescanner.data.mappers.UiPackageToDataPackageMapper
import com.learning.qrbarcodescanner.data.mappers.UiPackageToDataPackageMapperImpl
import com.learning.qrbarcodescanner.data.repository.DeliveryRepository
import com.learning.qrbarcodescanner.ui.model.PackageDelivery
import com.learning.qrbarcodescanner.ui.usecases.UpdatePackageUseCase
import javax.inject.Inject

class UpdatePackageUseCaseImpl @Inject constructor(
    private val repository: DeliveryRepository,
    private val mapper: UiPackageToDataPackageMapper = UiPackageToDataPackageMapperImpl()
) : UpdatePackageUseCase {

    override suspend fun update(delivery: PackageDelivery) {
        val packageDeliveryEntity = mapper.map(delivery)
        repository.update(packageDeliveryEntity)
    }
}