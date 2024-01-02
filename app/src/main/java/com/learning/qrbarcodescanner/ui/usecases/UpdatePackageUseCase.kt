package com.learning.qrbarcodescanner.ui.usecases

import com.learning.qrbarcodescanner.ui.model.PackageDelivery

/**
 * Interface defining the contract for the use case responsible for updating a package delivery.
 * Implementing classes are responsible for transforming [PackageDelivery] into the type required
 * by the database.
 */
interface UpdatePackageUseCase {

    /**
     * Updates an existing package delivery using the provided [PackageDelivery] object.
     *
     * @param delivery The [PackageDelivery] object representing the updated information.
     */
    suspend fun update(delivery: PackageDelivery)
}