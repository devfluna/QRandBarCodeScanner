package com.learning.qrbarcodescanner.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.learning.qrbarcodescanner.data.database.PackageDeliveryEntity
import com.learning.qrbarcodescanner.data.repository.DeliveryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PackagesViewModel @Inject constructor(
    private val deliveryRepository: DeliveryRepository
    ) : ViewModel() {

    val allPackagesList: LiveData<List<PackageDeliveryEntity>> = deliveryRepository.getAllPackages()
}