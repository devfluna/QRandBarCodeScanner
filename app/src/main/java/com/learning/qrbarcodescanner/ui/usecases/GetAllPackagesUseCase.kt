package com.learning.qrbarcodescanner.ui.usecases

import com.learning.qrbarcodescanner.ui.model.PackageDelivery
import kotlinx.coroutines.flow.Flow

/**
 * Interface defining the contract for the use case responsible for retrieving all packages.
 * Implementing classes are responsible for retrieving from the database and transforming
 * the objects to [PackageDelivery] to be used by the UI-layer.
 */
interface GetAllPackagesUseCase {

    /**
     * Invokes the use case to retrieve a flow of all package deliveries.
     *
     * @return A [Flow] emitting a list of [PackageDelivery] objects.
     */
    operator fun invoke(): Flow<List<PackageDelivery>>
}