package com.learning.qrbarcodescanner.data.repository

import com.learning.qrbarcodescanner.data.database.DataDeliveryStatus
import com.learning.qrbarcodescanner.data.database.DeliveryDao
import com.learning.qrbarcodescanner.data.database.PackageDeliveryEntity
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DeliveryRepositoryImplTest {

    @MockK
    private lateinit var deliveryRepository: DeliveryRepositoryImpl

    @RelaxedMockK
    private lateinit var deliveryDao: DeliveryDao

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        deliveryRepository = DeliveryRepositoryImpl(deliveryDao)
    }

    @Test
    fun getAllPackagesShouldReturnExpectedListOfObjects() {
        // Given
        val expectedList = listOf(
            PackageDeliveryEntity(1, "TestItem1", "TestTracking1", DataDeliveryStatus.RECEIVED),
            PackageDeliveryEntity(2, "TestItem2", "TestTracking2", DataDeliveryStatus.RECEIVED)
        )
        val expectedFlow: Flow<List<PackageDeliveryEntity>> = flow { emit(expectedList) }
        every { deliveryDao.getAllPackages() } returns expectedFlow

        // When
        val result = deliveryRepository.getAllPackages()

        // Then
        assertEquals(expectedFlow, result)
    }

    @Test
    fun locateShouldReturnExpectedObjectGivenSpecificTrackingNumber() {
        // Given
        val trackingNumber = "ABC123"
        val expectedDelivery =
            PackageDeliveryEntity(1, "TestItem", trackingNumber, DataDeliveryStatus.RECEIVED)
        every { deliveryDao.locate(trackingNumber) } returns expectedDelivery

        // When
        runBlocking {
            val result = deliveryRepository.locate(trackingNumber)

            // Then
            assertEquals(expectedDelivery, result)
        }
    }

    @Test
    fun repositoryShouldCallDaoWithExpectedInput() {
        // Given
        val expectedDelivery = PackageDeliveryEntity(1, "TestItem", "ABC123", DataDeliveryStatus.RECEIVED)
        every { runBlocking { deliveryDao.insert(any()) } } just Runs
        every { runBlocking { deliveryDao.update(any()) } } just Runs
        every { runBlocking { deliveryDao.delete(any()) } } just Runs

        // When
        runBlocking { deliveryRepository.insert(expectedDelivery) }
        runBlocking { deliveryRepository.update(expectedDelivery) }
        runBlocking { deliveryRepository.delete(expectedDelivery) }

        // Then
        verify { runBlocking { deliveryDao.insert(expectedDelivery) } }
        verify { runBlocking { deliveryDao.update(expectedDelivery) } }
        verify { runBlocking { deliveryDao.delete(expectedDelivery) } }
    }
}