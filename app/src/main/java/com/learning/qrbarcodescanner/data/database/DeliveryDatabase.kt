package com.learning.qrbarcodescanner.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PackageDeliveryEntity::class], version = 1, exportSchema = false)
abstract class DeliveryDatabase: RoomDatabase() {
    abstract fun packageDeliveryDao(): DeliveryDao
}