package com.learning.qrbarcodescanner.ui.usecases

import com.learning.qrbarcodescanner.ui.model.PackageDelivery

interface UpdatePackageUseCase {
    suspend fun update(delivery: PackageDelivery)
}