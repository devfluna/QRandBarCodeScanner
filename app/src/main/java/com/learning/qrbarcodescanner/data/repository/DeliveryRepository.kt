package com.learning.qrbarcodescanner.data.repository

import com.learning.qrbarcodescanner.data.database.PackageDeliveryEntity
import kotlinx.coroutines.flow.Flow

/**
 * Interface defining the contract for interacting with a delivery repository,
 * providing methods to retrieve, insert, and update package delivery entities.
 *
 * Implementing classes are responsible for communicating with the database
 * to fulfill contract methods.
 */
interface DeliveryRepository {

    /**
     * Retrieves a flow of all package delivery entities.
     *
     * @return A [Flow] emitting a list of [PackageDeliveryEntity] objects.
     */
    fun getAllPackages(): Flow<List<PackageDeliveryEntity>>

    /**
     * Inserts a new package delivery entity into the repository.
     *
     * @param packageDeliveryEntity The [PackageDeliveryEntity] to be inserted.
     */
    suspend fun insert(packageDeliveryEntity: PackageDeliveryEntity)

    /**
     * Updates an existing package delivery entity in the repository.
     *
     * @param packageDeliveryEntity The [PackageDeliveryEntity] to be updated.
     */
    suspend fun update(packageDeliveryEntity: PackageDeliveryEntity)

    /**
     * Deletes an existing package delivery entity in the repository.
     *
     * @param packageDeliveryEntity The [PackageDeliveryEntity] object to be deleted.
     */
    suspend fun delete(packageDeliveryEntity: PackageDeliveryEntity)

    /**
     * Locates an existing package delivery using the provided [trackingNumber].
     *
     * @param trackingNumber Of the [PackageDeliveryEntity] object to be located.
     */
    suspend fun locate(trackingNumber: String): PackageDeliveryEntity
}