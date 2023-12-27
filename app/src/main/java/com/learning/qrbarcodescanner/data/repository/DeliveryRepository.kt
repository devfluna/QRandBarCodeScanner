package com.learning.qrbarcodescanner.data.repository

import androidx.lifecycle.LiveData
import com.learning.qrbarcodescanner.data.database.PackageDeliveryEntity

interface DeliveryRepository {
    fun getAllPackages(): LiveData<List<PackageDeliveryEntity>>

    suspend fun insert(packageDeliveryEntity: PackageDeliveryEntity)
}