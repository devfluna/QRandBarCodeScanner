package com.learning.qrbarcodescanner.ui.usecases

import com.learning.qrbarcodescanner.ui.model.PackageDelivery
import kotlinx.coroutines.flow.Flow

interface GetAllPackagesUseCase {
    operator fun invoke(): Flow<List<PackageDelivery>>
}