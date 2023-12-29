package com.learning.qrbarcodescanner.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "package_entity")
data class PackageDeliveryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 1,
    val itemName: String,
    val trackingNumber: String,
    val status: DataDeliveryStatus,
)
