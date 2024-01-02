package com.learning.qrbarcodescanner.data.repository

import com.learning.qrbarcodescanner.data.database.PackageDeliveryEntity
import kotlinx.coroutines.flow.Flow

interface DeliveryRepository {
    fun getAllPackages(): Flow<List<PackageDeliveryEntity>>

    suspend fun insert(packageDeliveryEntity: PackageDeliveryEntity)

    suspend fun update(packageDeliveryEntity: PackageDeliveryEntity)
}