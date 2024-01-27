package com.learning.qrbarcodescanner.data.usecases

import com.learning.qrbarcodescanner.data.database.DataDeliveryStatus
import com.learning.qrbarcodescanner.data.database.PackageDeliveryEntity
import com.learning.qrbarcodescanner.data.mappers.DataPackageToUiPackageMapper
import com.learning.qrbarcodescanner.data.repository.DeliveryRepository
import com.learning.qrbarcodescanner.data.repository.DeliveryRepositoryImpl
import com.learning.qrbarcodescanner.ui.model.DeliveryStatus
import com.learning.qrbarcodescanner.ui.model.PackageDelivery
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class GetAllPackagesUseCaseImplTest {

    private val repository: DeliveryRepository = mockk(relaxed = true)
    private val mapper: DataPackageToUiPackageMapper = mockk()

    private lateinit var getAllPackagesUseCaseImpl: GetAllPackagesUseCaseImpl

    @Before
    fun setUp() {
        getAllPackagesUseCaseImpl = GetAllPackagesUseCaseImpl(repository, mapper)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Ignore("Find a better way to test Flow. False positive test")
    @Test
    fun `invoke should return expected list from deliveryRepository`() = runBlocking {
        // Given
        val dataPackagesList = listOf(
            PackageDeliveryEntity(1, "Item1", "123456", DataDeliveryStatus.DELIVERED),
        )

        val mappedPackagesList = listOf(
            PackageDelivery(1, "Item2222", "123456", DeliveryStatus.Delivered()),
        )

        every { repository.getAllPackages() } returns flow {
            emit(dataPackagesList)
        }
        every { mapper.map(any()) } returns mappedPackagesList[0] //answers { callOriginal() }

        // When
        getAllPackagesUseCaseImpl.invoke().collectLatest {
            // Then
            assertEquals(mappedPackagesList[0].itemName, it[0].itemName)
        }
    }
}
