package com.learning.qrbarcodescanner.data.usecases

import com.learning.qrbarcodescanner.data.mappers.DataPackageToUiPackageMapper
import com.learning.qrbarcodescanner.data.mappers.DataPackageToUiPackageMapperImpl
import com.learning.qrbarcodescanner.data.repository.DeliveryRepository
import com.learning.qrbarcodescanner.ui.model.PackageDelivery
import com.learning.qrbarcodescanner.ui.usecases.GetAllPackagesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllPackagesUseCaseImpl @Inject constructor(
    private val deliveryRepository: DeliveryRepository,
    private val mapper: DataPackageToUiPackageMapper = DataPackageToUiPackageMapperImpl()
) : GetAllPackagesUseCase {

    override fun invoke(): Flow<List<PackageDelivery>> {
        return deliveryRepository.getAllPackages().map { dataPackagesList ->
            dataPackagesList
                .sortedByDescending { it.status }
                .map { packageEntity -> mapper.map(packageEntity) }
        }
    }
}