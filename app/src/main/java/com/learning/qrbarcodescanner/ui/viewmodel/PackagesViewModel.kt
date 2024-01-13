package com.learning.qrbarcodescanner.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.qrbarcodescanner.ui.model.PackageDelivery
import com.learning.qrbarcodescanner.ui.usecases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PackagesViewModel @Inject constructor(
    private val getAllPackagesUseCase: GetAllPackagesUseCase,
    private val insertPackageUseCase: InsertPackageUseCase,
    private val updatePackageUseCase: UpdatePackageUseCase,
    private val deletePackageUseCase: DeletePackageUseCase,
    private val trackingNumberUseCase: HandleTrackingNumberUseCase
) : ViewModel() {

    val allPackagesList: Flow<List<PackageDelivery>> = getAllPackagesUseCase()

    fun insertNew(delivery: PackageDelivery) {
        viewModelScope.launch(Dispatchers.IO) {
            insertPackageUseCase.insert(delivery)
        }
    }

    fun update(delivery: PackageDelivery) {
        viewModelScope.launch(Dispatchers.IO) {
            updatePackageUseCase.update(delivery)
        }
    }

    fun delete(delivery: PackageDelivery) {
        viewModelScope.launch(Dispatchers.IO) {
            deletePackageUseCase.delete(delivery)
        }
    }

    fun check(trackingNumber: String) {
        viewModelScope.launch(Dispatchers.IO) {
            trackingNumberUseCase.invoke(trackingNumber)
        }
    }
}