package com.learning.qrbarcodescanner.data.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DeliveryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(delivery: PackageDeliveryEntity)

    @Delete
    suspend fun delete(delivery: PackageDeliveryEntity)

    @Query("SELECT * from package_entity ORDER BY status ASC")
    fun getAllPackages(): LiveData<List<PackageDeliveryEntity>>
}

