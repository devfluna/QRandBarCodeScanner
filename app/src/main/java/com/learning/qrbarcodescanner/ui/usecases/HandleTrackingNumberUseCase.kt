package com.learning.qrbarcodescanner.ui.usecases


/**
 * Interface defining the contract for the use case responsible for checking if the Tracking Number
 * provided already exists in the database.
 *
 */
interface HandleTrackingNumberUseCase {

    /**
     * Performs an assessment if the Tracking Number exists in the database.
     * If Tracking Number exists, the UseCase will update the object status as Received.
     * If Tracking Number does not exists, the UseCase will create a new record in the database.
     *
     * @param trackingNumber The String in the object to be located.
     */
    suspend fun invoke(trackingNumber: String)
}