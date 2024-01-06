package com.learning.qrbarcodescanner.data.repository

import com.learning.qrbarcodescanner.data.database.DeliveryDao
import com.learning.qrbarcodescanner.data.database.PackageDeliveryEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeliveryRepositoryImpl @Inject constructor(private val deliveryDao: DeliveryDao) :
    DeliveryRepository {
    override fun getAllPackages(): Flow<List<PackageDeliveryEntity>> {
        return deliveryDao.getAllPackages()
    }

    override suspend fun insert(packageDeliveryEntity: PackageDeliveryEntity) {
        deliveryDao.insert(packageDeliveryEntity)
    }

    override suspend fun update(packageDeliveryEntity: PackageDeliveryEntity) {
        deliveryDao.update(packageDeliveryEntity)
    }

    override suspend fun delete(packageDeliveryEntity: PackageDeliveryEntity) {
        deliveryDao.delete(packageDeliveryEntity)
    }
}