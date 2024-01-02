package com.learning.qrbarcodescanner.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DeliveryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(delivery: PackageDeliveryEntity)

    @Delete
    suspend fun delete(delivery: PackageDeliveryEntity)

    @Update
    suspend fun update(delivery: PackageDeliveryEntity)

    @Query("SELECT * from package_entity ORDER BY status ASC")
    fun getAllPackages(): Flow<List<PackageDeliveryEntity>>
}

