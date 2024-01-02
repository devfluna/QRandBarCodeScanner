package com.learning.qrbarcodescanner.data.usecases

import com.learning.qrbarcodescanner.data.mappers.UiPackageToDataPackageMapper
import com.learning.qrbarcodescanner.data.mappers.UiPackageToDataPackageMapperImpl
import com.learning.qrbarcodescanner.data.repository.DeliveryRepository
import com.learning.qrbarcodescanner.ui.model.PackageDelivery
import com.learning.qrbarcodescanner.ui.usecases.InsertPackageUseCase
import javax.inject.Inject

class InsertPackageUseCaseImpl @Inject constructor(
    private val deliveryRepository: DeliveryRepository,
    private val mapper: UiPackageToDataPackageMapper = UiPackageToDataPackageMapperImpl()
) : InsertPackageUseCase {

    override suspend fun insert(delivery: PackageDelivery) {
        val packageDeliveryEntity = mapper.map(delivery)
        deliveryRepository.insert(packageDeliveryEntity)
    }
}