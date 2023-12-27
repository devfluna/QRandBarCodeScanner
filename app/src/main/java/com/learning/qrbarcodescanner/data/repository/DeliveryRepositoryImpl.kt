package com.learning.qrbarcodescanner.data.repository

import androidx.lifecycle.LiveData
import com.learning.qrbarcodescanner.data.database.DeliveryDao
import com.learning.qrbarcodescanner.data.database.PackageDeliveryEntity
import javax.inject.Inject

class DeliveryRepositoryImpl @Inject constructor(private val deliveryDao: DeliveryDao): DeliveryRepository {
    override fun getAllPackages(): LiveData<List<PackageDeliveryEntity>> {
        return deliveryDao.getAllPackages()
    }

    override suspend fun insert(packageDeliveryEntity: PackageDeliveryEntity) {
        deliveryDao.insert(packageDeliveryEntity)
    }
}