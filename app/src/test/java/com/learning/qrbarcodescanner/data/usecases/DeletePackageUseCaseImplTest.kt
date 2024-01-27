package com.learning.qrbarcodescanner.data.usecases

import com.learning.qrbarcodescanner.data.database.DataDeliveryStatus
import com.learning.qrbarcodescanner.data.database.PackageDeliveryEntity
import com.learning.qrbarcodescanner.data.mappers.UiStatusToDataStatusMapper
import com.learning.qrbarcodescanner.data.repository.DeliveryRepository
import com.learning.qrbarcodescanner.ui.model.DeliveryStatus
import com.learning.qrbarcodescanner.ui.model.PackageDelivery
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class DeletePackageUseCaseImplTest {

    private val repository: DeliveryRepository = mockk(relaxed = true)
    private val mapper: UiStatusToDataStatusMapper = mockk()

    private lateinit var deletePackageUseCase: DeletePackageUseCaseImpl

    @Before
    fun setUp() {
        deletePackageUseCase = DeletePackageUseCaseImpl(repository, mapper)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `delete package invokes repository delete`() = runBlocking {
        // Given
        val packageDelivery = PackageDelivery(
            id = 1,
            itemName = "TestItem",
            trackingNumber = "123456",
            status = DeliveryStatus.Delivered()
        )

        every { mapper.map(any()) } returns DataDeliveryStatus.DELIVERED

        // When
        deletePackageUseCase.delete(packageDelivery)

        // Then
        val expectedEntity = PackageDeliveryEntity(
            id = 1,
            itemName = "TestItem",
            trackingNumber = "123456",
            status = DataDeliveryStatus.DELIVERED
        )

        verify { runBlocking { repository.delete(expectedEntity) } }
    }
}