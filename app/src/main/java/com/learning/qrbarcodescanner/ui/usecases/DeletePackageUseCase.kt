package com.learning.qrbarcodescanner.ui.usecases

import com.learning.qrbarcodescanner.ui.model.PackageDelivery

/**
 * Interface defining the contract for the use case responsible for deleting a package delivery.
 * Implementing classes are responsible for transforming [PackageDelivery] into the type required
 * by the database.
 */
interface DeletePackageUseCase {

    /**
     * Deletes an existing package delivery using the provided [PackageDelivery] object.
     *
     * @param delivery The [PackageDelivery] object to be deleted.
     */
    suspend fun delete(delivery: PackageDelivery)
}