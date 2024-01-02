package com.learning.qrbarcodescanner.ui.usecases

import com.learning.qrbarcodescanner.ui.model.PackageDelivery

/**
 * Interface defining the contract for the use case responsible for inserting a package delivery.
 * Implementing classes are responsible for transforming [PackageDelivery] into the type required
 * by the database.
 */
interface InsertPackageUseCase {

    /**
     * Inserts a new package delivery using the provided [PackageDelivery] object.
     *
     * @param delivery The [PackageDelivery] object to be inserted.
     */
    suspend fun insert(delivery: PackageDelivery)
}