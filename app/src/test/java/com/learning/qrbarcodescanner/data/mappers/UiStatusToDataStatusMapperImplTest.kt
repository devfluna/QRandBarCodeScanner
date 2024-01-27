package com.learning.qrbarcodescanner.data.mappers

import com.learning.qrbarcodescanner.data.database.DataDeliveryStatus
import com.learning.qrbarcodescanner.ui.model.DeliveryStatus
import org.junit.Assert.*
import org.junit.Test

class UiStatusToDataStatusMapperImplTest {
    private val sutMapper = UiStatusToDataStatusMapperImpl()

    @Test
    fun mapShouldExpectedDataStatusGivenUiDeliveryStatus() {
        // Given
        val uiDeliveryStatus = listOf(
            DeliveryStatus.Shipped(),
            DeliveryStatus.Delivered(),
            DeliveryStatus.Notified(),
            DeliveryStatus.Received()
        )
        val expectedDataStatus = listOf(
            DataDeliveryStatus.SHIPPED,
            DataDeliveryStatus.DELIVERED,
            DataDeliveryStatus.NOTIFIED,
            DataDeliveryStatus.RECEIVED
        )

        // Then
        assertEquals(sutMapper.map(uiDeliveryStatus[0]), expectedDataStatus[0])
        assertEquals(sutMapper.map(uiDeliveryStatus[1]), expectedDataStatus[1])
        assertEquals(sutMapper.map(uiDeliveryStatus[2]), expectedDataStatus[2])
        assertEquals(sutMapper.map(uiDeliveryStatus[3]), expectedDataStatus[3])
    }
}