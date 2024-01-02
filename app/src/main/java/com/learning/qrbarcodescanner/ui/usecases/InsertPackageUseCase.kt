package com.learning.qrbarcodescanner.ui.usecases

import com.learning.qrbarcodescanner.ui.model.PackageDelivery

interface InsertPackageUseCase {
    suspend fun insert(delivery: PackageDelivery)
}