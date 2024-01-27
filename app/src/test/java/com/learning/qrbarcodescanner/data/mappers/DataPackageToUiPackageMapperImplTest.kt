package com.learning.qrbarcodescanner.data.mappers

import com.learning.qrbarcodescanner.data.database.DataDeliveryStatus
import com.learning.qrbarcodescanner.data.database.PackageDeliveryEntity
import com.learning.qrbarcodescanner.ui.model.DeliveryStatus
import com.learning.qrbarcodescanner.ui.model.PackageDelivery
import org.junit.Assert.assertEquals
import org.junit.Test

class DataPackageToUiPackageMapperImplTest {

    private val sutMapper = DataPackageToUiPackageMapperImpl()

    @Test
    fun mapShouldReturnExpectedPackageDeliveryGivenDataPackageDelivery() {
        // Given
        val itemName = "ItemName"
        val trackingNumber = "ABC123"
        val status = DataDeliveryStatus.RECEIVED
        val deliveryEntity = PackageDeliveryEntity(1, itemName, trackingNumber, status)

        // When
        val actualDelivery = sutMapper.map(deliveryEntity)

        // Then
        val expectedDelivery = PackageDelivery(1, itemName, trackingNumber, DeliveryStatus.Received())
        assertEquals(expectedDelivery.id, actualDelivery.id)
        assertEquals(expectedDelivery.itemName, actualDelivery.itemName)
        assertEquals(expectedDelivery.trackingNumber, actualDelivery.trackingNumber)
        assertEquals(expectedDelivery.status.javaClass, actualDelivery.status.javaClass)
    }

    @Test
    fun mapShouldReturnExpectedDeliveryStatusGivenDataDeliveryStatus() {
        // Given
        val deliveries = listOf(
            PackageDeliveryEntity(1, "A", "123", status = DataDeliveryStatus.SHIPPED),
            PackageDeliveryEntity(2, "B", "123", status = DataDeliveryStatus.DELIVERED),
            PackageDeliveryEntity(3, "C", "123", status = DataDeliveryStatus.NOTIFIED),
            PackageDeliveryEntity(4, "D", "123", status = DataDeliveryStatus.RECEIVED),
        )

        // Then
        assertEquals(
            DeliveryStatus.Shipped().javaClass, sutMapper.map(deliveries[0]).status.javaClass
        )

        assertEquals(
            DeliveryStatus.Delivered().javaClass, sutMapper.map(deliveries[1]).status.javaClass
        )

        assertEquals(
            DeliveryStatus.Notified().javaClass, sutMapper.map(deliveries[2]).status.javaClass
        )

        assertEquals(
            DeliveryStatus.Received().javaClass, sutMapper.map(deliveries[3]).status.javaClass
        )
    }
}